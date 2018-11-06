package com.example.wustNeo4j.object;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
@Data
public class node {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private int type;

    @Relationship(type = "人员编号", direction = Relationship.INCOMING)
    public Set<node> personIds;

    @Relationship(type = "姓名")
    public Set<node> personId;


    @Relationship(type = "关联案件编号", direction = Relationship.INCOMING)
    public Set<node> relateCases;

    @Relationship(type = "银行账号", direction = Relationship.INCOMING)
    public Set<node> bankNum;


    @Relationship(type = "财付通号", direction = Relationship.INCOMING)
    public Set<node> caifutong;

    @Relationship(type = "身份证号", direction = Relationship.INCOMING)
    public Set<node> idCard;

    @Relationship(type = "护照号", direction = Relationship.INCOMING)
    public Set<node> passPort;

    @Relationship(type = "手机号", direction = Relationship.INCOMING)
    public Set<node> phone;

    @Relationship(type = "QQ号", direction = Relationship.INCOMING)
    public Set<node> qq;

    @Relationship(type = "淘宝账号", direction = Relationship.INCOMING)
    public Set<node> taobao;

    @Relationship(type = "交易号", direction = Relationship.INCOMING)
    public Set<node> transactionAccount;

    @Relationship(type = "微信号", direction = Relationship.INCOMING)
    public Set<node> weixin;

    @Relationship(type = "支付宝账号", direction = Relationship.INCOMING)
    public Set<node> zhifubaoAccount;

    @Relationship(type = "支付宝ID", direction = Relationship.INCOMING)
    public Set<node> zhifubaoNum;

    @Relationship(type = "人员类别", direction = Relationship.INCOMING)
    public Set<node> roleType;

    @Relationship(type = "犯罪角色",direction = Relationship.INCOMING)
    public Set<node> crimeRole;

    @Relationship(type = "信息来源",direction = Relationship.INCOMING)
    public Set<node> infoSource;

    @Relationship(type = "关联其他手机号",direction = Relationship.INCOMING)
    public Set<node> relatePhone;

    @Relationship(type = "关联其他QQ号",direction = Relationship.INCOMING)
    public Set<node> relateQq;

    @Relationship(type = "关联其他微信号",direction = Relationship.INCOMING)
    public Set<node> relateWeixin;

    @Relationship(type = "关联子账户",direction = Relationship.INCOMING)
    public Set<node> relateSub;

    @Relationship(type = "指纹采集卡编号",direction = Relationship.INCOMING)
    public Set<node> fingerCollect;

    @Relationship(type = "DNA采集卡编号",direction = Relationship.INCOMING)
    public Set<node> DNACollect;

    @Relationship(type = "备注",direction = Relationship.INCOMING)
    public Set<node> node;

    @Relationship(type = "关联附件列表",direction = Relationship.INCOMING)
    public Set<node> relateAppendix;





    public String getCloudClueId() {
        return content;
    }

    public void setCloudClueId(String cloudClueId) {
        this.content = cloudClueId;
    }

    private node() {
        // Empty constructor required as of Neo4j API 2.0.5
    };


    public node(String cloudClueId) {
        this.content = cloudClueId;
    }
}
