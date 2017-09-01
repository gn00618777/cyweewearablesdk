package cwm.wearablesdk;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by user on 2017/8/31.
 */

public class CwmManager {

    InformationListener mListener = null;

    private final int NON_PENDING = 1;
    private final int PENDING = 2;

    final static int PACKET_SIZE = 20;

    private final Queue<Data> mOutPutQueue = new LinkedList<>();

    /******** protoco l************/
    private final int HEADER1 = 0xE6;
    private final int HEADER2 = 0x90;
    private final int ACK = 0xAC;
    private final int NACK = 0x15;
    //response id received from ring
    private final int SYNC_TIME_RESPONSE_ID = 0x02;
    private final int BODY_PARAMETER_RESPONSE_ID = 0x14;
    private final int INTELLIGENT_FEATURE_RESPONSE_ID = 0x12;
    private final int CLEAN_BOND_RESPONSE_ID = 0x70;
    // message id recevied from ring.
    private final int MOTION_DATA_REPORT_MESSAGE_ID = 0xAF;
    private final int BATTERY_STATUS_REPORT_MESSAGE_ID = 0xED;
    private final int TAP_EVENT_MESSAGE_ID = 0x01;
    private final int WRIST_SCROLL_EVENT_MESSAGE_ID = 0x02;
    private final int SEDENTARY_EVENT_MESSAGE_ID = 0x03;
    private final int HART_RATE_EVENT_MESSAGE_ID = 0x04;



    // interface -----------------------------------------------------------------------------------
    public interface InformationListener {
        void onGetCwmRunData(CwmInformation runInfo);
        void onGetCwmWalkData(CwmInformation walkInfo);
        void onGetBikeData(CwmInformation bikeInfo);
        void onGetHeartData(CwmInformation heartInfo);
        void onGetActivity(CwmInformation activityInfo);
    } // onDataArrivalListener()

    // function
    public void registerInfomationListener(InformationListener listener){
        mListener = listener;
    }
    public void receiveRawByte(byte[] rxBuffer){
        int packet_type = 0;
        int packet_length = 0;
        int packet_id_type = 0;
        int packet_message_id = 0;
        byte[] packet = null;


        if((rxBuffer[0] & 0xFF) == HEADER1 && (rxBuffer[1] & 0xFF) == HEADER2 && (((rxBuffer[3] & 0xFF) == ACK) || ((rxBuffer[3]) == NACK))) {
            packet_type = NON_PENDING;
            packet_length = rxBuffer[2] & 0xFF;
            packet_id_type = rxBuffer[3] & 0xFF;
            packet_message_id = rxBuffer[4] & 0xFF;
            packet = rxBuffer;
            Data data = new Data(packet_type, packet_length, packet_id_type, packet_message_id, packet);
            enqueue(data);
        }
        parser();

    }
    public boolean CwmBleStatus(){
        return true;
    }
    public boolean CwmDeviceUnregister(){
        return true;
    }
    private void enqueue(Data data){
        if (data.type == NON_PENDING && data.length <= PACKET_SIZE) {
            mOutPutQueue.add(data);
        }
    }
    private void parser(){
        if(mOutPutQueue.size() != 0){
            Data data = mOutPutQueue.poll();
            if(data.getIdType() == ACK) {
                int id = data.getMessageID();
                switch (id) {
                    case SYNC_TIME_RESPONSE_ID:
                        break;
                    case BODY_PARAMETER_RESPONSE_ID:
                        break;
                    case INTELLIGENT_FEATURE_RESPONSE_ID:
                        break;
                    case CLEAN_BOND_RESPONSE_ID:
                        break;
                    default:
                        break;
                }
            }
            else if(data.getIdType() == NACK){

            }
            else{
                int id = data.getIdType();
                byte[] value = data.getValue();
                CwmInformation cwmInfo;
                switch (id){
                    case MOTION_DATA_REPORT_MESSAGE_ID:
                         cwmInfo = getInfomation(MOTION_DATA_REPORT_MESSAGE_ID, value);
                        mListener.onGetCwmWalkData(cwmInfo);
                        break;
                    case BATTERY_STATUS_REPORT_MESSAGE_ID:
                        cwmInfo = getInfomation(BATTERY_STATUS_REPORT_MESSAGE_ID, value);
                        break;
                    case TAP_EVENT_MESSAGE_ID:
                        cwmInfo = getInfomation(TAP_EVENT_MESSAGE_ID, value);
                        mListener.onGetActivity(cwmInfo);
                        break;
                    case WRIST_SCROLL_EVENT_MESSAGE_ID:
                        cwmInfo = getInfomation(WRIST_SCROLL_EVENT_MESSAGE_ID, value);
                        mListener.onGetActivity(cwmInfo);
                        break;
                    case SEDENTARY_EVENT_MESSAGE_ID:
                        cwmInfo = getInfomation(SEDENTARY_EVENT_MESSAGE_ID, value);
                        mListener.onGetActivity(cwmInfo);
                        break;
                    case HART_RATE_EVENT_MESSAGE_ID:
                        cwmInfo = getInfomation(HART_RATE_EVENT_MESSAGE_ID, value);
                        mListener.onGetHeartData(cwmInfo);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private CwmInformation getInfomation(int messageId, byte[] value){
        if(messageId == MOTION_DATA_REPORT_MESSAGE_ID ){

            byte[] dest = new byte[4];

            System.arraycopy(value, 4, dest, 0, 4);
            int walkStep = (int) ByteBuffer.wrap(dest).order(ByteOrder.LITTLE_ENDIAN).getFloat();
            System.arraycopy(value, 8, dest, 0, 4);
            int distance = (int)ByteBuffer.wrap(dest).order(ByteOrder.LITTLE_ENDIAN).getFloat();
            System.arraycopy(value, 12, dest, 0, 4);
            int calories = (int)ByteBuffer.wrap(dest).order(ByteOrder.LITTLE_ENDIAN).getFloat();
            int status = value[16] & 0xFF;
            CwmInformation cwmInfo = new CwmInformation();
            cwmInfo.setId(MOTION_DATA_REPORT_MESSAGE_ID);
            cwmInfo.setWalkStep(walkStep);
            cwmInfo.setDistance(distance);
            cwmInfo.setCalories(calories);
            cwmInfo.setStatus(status);
            return cwmInfo;
        }
        else if(messageId == TAP_EVENT_MESSAGE_ID){
            CwmInformation cwmInfo = new CwmInformation();
            cwmInfo.setId(TAP_EVENT_MESSAGE_ID);
            return cwmInfo;
        }
        else if(messageId == WRIST_SCROLL_EVENT_MESSAGE_ID){
            CwmInformation cwmInfo = new CwmInformation();
            cwmInfo.setId(WRIST_SCROLL_EVENT_MESSAGE_ID);
            return cwmInfo;
        }
        else if(messageId == HART_RATE_EVENT_MESSAGE_ID){
            int heartBeat = 0;
            byte[] dest = new byte[4];

            System.arraycopy(value, 4, dest, 0, 4);
            heartBeat = (int) ByteBuffer.wrap(dest).order(ByteOrder.LITTLE_ENDIAN).getFloat();
            CwmInformation cwmInfo = new CwmInformation();
            cwmInfo.setId(HART_RATE_EVENT_MESSAGE_ID);
            cwmInfo.setHeartBeat(heartBeat);
            return cwmInfo;
        }
        else if(messageId == SEDENTARY_EVENT_MESSAGE_ID){
            CwmInformation cwmInfo = new CwmInformation();
            cwmInfo.setId(SEDENTARY_EVENT_MESSAGE_ID);
            return cwmInfo;
        }
        return null;
    }

    public class Data{

          private int type;
          private int length;
          private int idType; // to differentiate between ack & nack and message id
          private int messageID;
          private byte[] value;

        private Data(int type, int length, int idType, int messageID, byte[] value) {
            this.type = type;
            this.length = length;
            this.idType = idType;
            this.messageID = messageID;
            this.value = value;
        }
        private Data(int type, byte[] value){
            this.type = type;
            this.length = 0;
            this.idType = 0;
            this.messageID = 0;
            this.value = value;
        }
        private Data(int type, int length, int id, byte[] value){
            this.type = type;
            this.length = length;
            this.idType = id;
            this.messageID = 0;
            this.value = value;
        }

        public int getIdType(){
            return idType;
        }

        public int getMessageID(){
            if(idType == ACK ||  idType == NACK)
                return messageID;
            else
                return idType;
        }

        public byte[] getValue(){return value;}

    }
}
