package org.example.ZZJdbc.listener;

import lombok.extern.log4j.Log4j2;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import java.sql.SQLException;

@Log4j2
public class CustomRowSetListener implements RowSetListener {
    @Override
    public void rowSetChanged(RowSetEvent event) {
       log.info("Command execute triggered");
    }

    @Override
    public void rowChanged(RowSetEvent event) {
        log.info("Row inserted, updated or deleted");
        if (event.getSource() instanceof javax.sql.rowset.JdbcRowSet) {
            try {
                ((javax.sql.rowset.JdbcRowSet) event.getSource()).execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {
        log.info("Cursor moved");

    }
}
