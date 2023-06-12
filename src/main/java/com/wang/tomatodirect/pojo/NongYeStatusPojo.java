package com.wang.tomatodirect.pojo;

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.annotation.rely.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author NLER
 * @date 2023/6/12 21:48
 */
@IndexName(value = "nongye_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NongYeStatusPojo {

    @IndexId(type = IdType.NONE)
    private String id;

    /**
     * 温度
     */
    @IndexField(fieldType = FieldType.TEXT, fieldData = true,value = "temp")
    private String temp;

    /**
     * 湿度
     */
    @IndexField(fieldType = FieldType.TEXT, fieldData = true,value = "humi")
    private String humi;

    /**
     * 光强
     */
    @IndexField(fieldType = FieldType.TEXT, fieldData = true,value = "intensity")
    private String intensity;

    @IndexField(fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis",value = "createDate")
    private String createDate;

}
