package pers.ayres.daily.ch2.constant;

/**
 * Created by Ye on 2018/3/14.
 */

public enum OrderStateEnum {
    TO_RECEIVE(0,"待接单","toReceive"),
    IMPLEMENT(1,"实施中","implement"),
    FINISHED(2,"已完成","finished"),
    CANCELED(3,"已取消","canceled")
    ;

    private int state;
    private String name;
    private String handlerKey;

    private OrderStateEnum(int state, String name, String handlerKey) {
        this.state = state;
        this.name = name;
        this.handlerKey = handlerKey;
    }

    public static String getHandlerKey(int state){
        for (OrderStateEnum en : OrderStateEnum.values()){
            if(en.state == state){
                return en.handlerKey;
            }
        }
        return null;
    }

    public int getState(){
        return state;
    }

    public String getName() {
        return name;
    }

    public String getHandlerKey() {
        return handlerKey;
    }
}
