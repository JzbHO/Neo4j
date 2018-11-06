   package com.example.wustNeo4j.service;

import com.example.wustNeo4j.NodeRepository;
import com.example.wustNeo4j.common.NodeType;
import com.example.wustNeo4j.common.Sout;
import com.example.wustNeo4j.exception.WebException;
import com.example.wustNeo4j.object.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2018/10/16 0016.
 */
@Service
public class UploadExcelService {


    @Autowired
    private NodeService nodeService;


    public String batchImport(String fileName, MultipartFile file) throws IOException {

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new WebException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);

        String beforeCloudId = "";
        sheet.getRow(1).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
        String thisCloudId = sheet.getRow(1).getCell(1).getStringCellValue();
        node cloudClue = new node(thisCloudId);
        Set<node> personIds = new HashSet<>();
        Set<node> relateCases = new HashSet<>();
        int count = 0;
        boolean beginFlag = true;

        //对每一个sheet循环
        //sheet.getLastRowNum()
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {


            if (!beginFlag) {
                beforeCloudId = thisCloudId;
                Row nextRow = sheet.getRow(r);
                nextRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);


                thisCloudId = nextRow.getCell(1).getStringCellValue();
                System.out.println("thisCloudId========" + thisCloudId + "row=" + r);
            }

            if (beginFlag) {
                beginFlag = false;
            }


            //如果与之前的并非同一个云端编号
            if (!thisCloudId.equals(beforeCloudId) && beforeCloudId != "") {
                System.out.println("this cloud id==" + thisCloudId);
                System.out.println("befo cloud id==" + beforeCloudId);

                count = 0;
                personIds.clear();
                relateCases.clear();
            }

            //取得sheet的每一行
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }


            //取得一行中每列的字段,从第二列开始,云端编号

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            if (count == 0) {
                if (StringUtils.isEmpty(thisCloudId)) {
                    UUID uuid = UUID.randomUUID();
                    thisCloudId = uuid.toString();
                }
                cloudClue = new node(thisCloudId);
                cloudClue.setType(NodeType.CLOUD.getType());
                nodeService.saveNode(cloudClue);
                count++;
            }


            relateCases.addAll(addColumn(row, 2));
            Set<node> personId = addColumn(row, 3);
            personIds.addAll(personId);
            //4
            Set<node> idCard = addColumn(row, 5);
            Set<node> allPhone = addColumn(row, 6);
            Set<node> zhifubaoAccounts = addColumn(row, 7);
            Set<node> taobao = addColumn(row, 8);
            Set<node> Qqs = addColumn(row, 9);
            Set<node> zhifubaoNum = addColumn(row, 10);
            Set<node> bankNum = addColumn(row, 11);
            Set<node> weixin = addColumn(row, 12);
            Set<node> passPort = addColumn(row, 13);
            Set<node> caifutong = addColumn(row, 14);
            Set<node> transactionAccount = addColumn(row, 15);
            Set<node> roleType = addColumn(row, 16);
            Set<node> crimeRole = addColumn(row, 17);
            Set<node> inforSource = addColumn(row, 18);
            Set<node> otherPhone = addColumn(row, 19);
            Set<node> relateQq = addColumn(row, 20);
            Set<node> relateWeixin = addColumn(row, 21);
            Set<node> subAccount = addColumn(row, 22);
            Set<node> finger = addColumn(row, 23);
            Set<node> DNA = addColumn(row, 24);
            Set<node> appendix = addColumn(row, 25);
            addColumn(row, 26);


            //姓名，这是主节点之一
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            String personname = row.getCell(4).getStringCellValue();
            System.out.println("name====" + personname);
            node personName = new node(personname);
            personName.setType(NodeType.NAME.getType());


            //这里开始存储关系
            if (bankNum != null) {
                personName.setBankNum(bankNum);
            }
            if (caifutong != null) {
                personName.setCaifutong(caifutong);
            }
            if (idCard != null) {
                personName.setIdCard(idCard);
            }
            if (personId != null) {
                personName.setPersonId(personId);
            }
            if (passPort != null) {
                personName.setPassPort(passPort);
            }
            if (Qqs.size() != 0) {
                personName.setQq(Qqs);
            }
            if (zhifubaoNum != null) {
                personName.setZhifubaoNum(zhifubaoNum);
            }
            if (zhifubaoAccounts!=null&&zhifubaoAccounts.size() != 0) {
                personName.setZhifubaoAccount(zhifubaoAccounts);
            }
            if (weixin != null) {
                personName.setWeixin(weixin);
            }
            if (taobao != null) {
                personName.setTaobao(taobao);
            }
            if (transactionAccount != null) {
                personName.setTransactionAccount(transactionAccount);
            }
            if (allPhone!=null&&allPhone.size() != 0) {
                personName.setPhone(allPhone);
            }
            if (transactionAccount != null) {
                personName.setTransactionAccount(transactionAccount);
            }
            if (roleType != null) {
                personName.setRoleType(roleType);
            }
            if (inforSource != null) {
                personName.setInfoSource(inforSource);
            }

            if (otherPhone != null) {
                personName.setRelatePhone(otherPhone);
            }
            if (relateQq != null) {
                personName.setRelateQq(relateQq);
            }
            if (relateWeixin != null) {
                personName.setRelateWeixin(relateWeixin);
            }
            if (subAccount != null) {
                personName.setRelateSub(subAccount);
            }
            if (finger != null) {
                personName.setFingerCollect(finger);
            }

            if (DNA != null) {
                personName.setDNACollect(DNA);
            }
            if (crimeRole!= null) {
                personName.setCrimeRole(crimeRole);
            }

//
//            if(note!=null){
//                personName.setNode(note);
//            }

            if (appendix != null) {
                personName.setRelateAppendix(appendix);
            }


            nodeService.saveNode(personName);


            //云端编号关系存储


            if (relateCases != null && relateCases.size() != 0) {
                cloudClue.setRelateCases(relateCases);
            }

                cloudClue.setPersonIds(personIds);

                nodeService.saveNode(cloudClue);


            }
        return "test";
    }

    private Set<node> addColumn(Row row,int i){
            //如果是人员编号，这里要带默认值
        Set<node> nodeSet=new HashSet<>();
        if(i==3){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String cloudId=row.getCell(1).getStringCellValue();
                row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                String personId = row.getCell(i).getStringCellValue();
                if(StringUtils.isEmpty(personId)){
                    personId="*"+cloudId;
                }
            node personNode = new node(personId);
            personNode.setType(NodeType.PERSON_NUM.getType());
                nodeSet.add(personNode);
            return nodeSet;
        }

        Sout.out("=========="+i);
        if(row.getCell(i)==null){
            return null;
        }
            row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            String content = row.getCell(i).getStringCellValue();


            if (!StringUtils.isEmpty(content)) {
                if(content.contains("；")){
                    for(String s:  content.split("；")){
                        node thisNode = new node(s);
                        thisNode.setType(NodeType.getRelation(i).getType());
                        nodeService.saveNode(thisNode);
                        nodeSet.add(thisNode);
                    }
                }else {
                    node thisNode = new node(content);
                    thisNode.setType(NodeType.getRelation(i).getType());
                    nodeService.saveNode(thisNode);
                    nodeSet.add(thisNode);
                }
            }

        return nodeSet;
    }
}



