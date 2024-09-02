package com.mine.jco_framework.service;

import com.mine.jco_framework.utils.SapUtils;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lynn
 * @date 2024/8/28/13:27
 */
@Service
public class IndexService {
    @Autowired
    private SapUtils sapUtils;

    public String demoService() {
        try {
            JCoDestination destination = sapUtils.getDestination();
            JCoFunction function = destination.getRepository().getFunctionTemplate("SAP FUNCTION NAME").getFunction();

            function.getImportParameterList().setValue("IMPORT", "YOUR VALUE");

            JCoTable table = function.getTableParameterList().getTable("TABLE NAME");
            table.appendRow();
            table.setValue("TABLE COLUMN NAME", "YOUR VALUE");
            table.setValue("TABLE COLUMN NAME", "YOUR VALUE");

            function.execute(destination);

            JCoTable return_table = function.getExportParameterList().getTable("EXPORT");
            if (return_table != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < return_table.getRow(); i++) {
                    return_table.setRow(i);
                    String column = return_table.getString("EXPORT COLUMN");
                    stringBuilder.append(column);
                }
                return stringBuilder.toString();
            } else {
                return "sap execute success, but get none";
            }
        } catch (JCoException e) {
            e.printStackTrace();
            return "program run failed!";
        }
    }
}
