package cn.jimoos.huaweiobs.dic;

import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/7/19 10:01:12
 * @description 上传文件类型
 */
@Data
public class UploadType {

    private UploadType(){

    }


    /**
     * 图片
     */
    public static final int PICTURE = 0;

    /**
     * 视频
     */
    public static final int VIDEO = 1;

    /**
     * 混合
     */
    public static final int MIX = 2;

}
