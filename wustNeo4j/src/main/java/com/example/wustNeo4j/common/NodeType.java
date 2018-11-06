package com.example.wustNeo4j.common;

/**
 * Created by Administrator on 2018/10/17 0017.
 */
public enum NodeType {

    CLOUD(1,"云端线索编号"),
    RELATE_CASE(2,"关联案件编号"),
    PERSON_NUM(3,"人员编号"),
    NAME(4,"姓名"),
    ID_CARD(5,"身份证号"),
    PHONE_NUM(6,"手机号"),
    ZHIFUBAO_ACCOUNT(7,"支付宝账号"),
    TAOBAO_ID(8,"淘宝ID"),
    QQ_NUM(9,"QQ号"),
    ZHIFUBAO_ID(10,"支付宝ID"),
    BANK_ACCOUNT(11,"银行账号"),
    WEIXIN(12,"微信号"),
    PASSPORT_NUM(13,"护照号"),
    CAIFUTONG(14,"财付通号"),
    BUSINESS_ACCOUNT(15,"交易账号"),
    PERSON_TYPE(16,"人员类别"),
    CRIME_ROLE(17,"犯罪角色"),
    INFOR_SOURCE(18,"信息来源"),
    REATE_PHONE_NUM(19,"关联其它手机号"),
    RELATE_QQ_NUM(20,"关联其它qq号"),
    RELATE_WEIXIN_NUM(21,"关联微信号"),
    RELATE_SUB_ACCOUNT(22,"关联子账户"),
    FINGER_PRINT(23,"指纹采集卡编号"),
    DNA_COLLECT(24,"DNA采集卡编号"),
    NOTE(25,"备注"),
    RELATE_APPENDIX(26,"关联附件列表");

    private int nodeType;
    private String relation;


    NodeType(int nodeType,String relation) {
        this.nodeType=nodeType;
        this.relation=relation;
    }
    NodeType(int nodeType) {
        this.nodeType=nodeType;
    }

    public int getType(){
        return this.nodeType;
    }

    public String relation(){
        return this.relation;
    }


    public static NodeType getRelation(int code){
        for(NodeType nodeType:NodeType.values()){
            if(nodeType.getType()==code){
                return nodeType;
            }
        }
        return null;
    }




}
