package wangjing.shareprefrenceutil.global;

public class GB {
    private static IGlobal globalCallBack;

    public static void setCallBack(IGlobal globalCallBack) {
        GB.globalCallBack = globalCallBack;
    }

    public static IGlobal getCallBack() {
        return globalCallBack;
    }
}
