/**
 * Copyright (C) 2014 - 2016 Universitaet Duisburg-Essen (semapp|uni-due.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unidue.rc;


import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.access.DbGenerator;
import org.apache.cayenne.access.dbsync.BaseSchemaUpdateStrategy;
import org.apache.cayenne.map.DataMap;
import org.apache.cayenne.map.DbEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A <code>CreateSchemaStrategy</code> can be used to force a schema generation onto an existing database.
 *
 * @author Nils Verheyen
 * @since 18.02.14 08:39
 */
public class CreateSchemaStrategy extends BaseSchemaUpdateStrategy {
    final static Log LOGGER = LogFactory.getLog(CreateSchemaStrategy.class);

    @Override
    protected void processSchemaUpdate(DataNode dataNode) throws SQLException {

        Map<String, Boolean> nameTables = getNameTablesInDB(dataNode);
        Collection<DbEntity> entities = dataNode.getEntityResolver().getDbEntities();
        boolean dropExisting = false;
        Iterator<DbEntity> it = entities.iterator();
        while (it.hasNext()) {
            if (nameTables.get(it.next().getName()) != null) {
                dropExisting = true;
                break;
            }
        }

        if (dropExisting)
            LOGGER.info("No schema detected, will create mapped tables");
        else
            LOGGER.info("Full or partial schema detected, skipping tables creation");

        generate(dataNode, dropExisting);
    }

    private void generate(DataNode dataNode, boolean dropExisting) {
        Collection<DataMap> map = dataNode.getDataMaps();
        Iterator<DataMap> iterator = map.iterator();
        while (iterator.hasNext()) {
            DbGenerator gen = new DbGenerator(dataNode.getAdapter(), iterator.next(),
                    dataNode.getJdbcEventLogger());
            gen.setShouldCreateTables(true);
            gen.setShouldDropTables(dropExisting);
            gen.setShouldCreateFKConstraints(true);
            gen.setShouldCreatePKSupport(true);
            gen.setShouldDropPKSupport(dropExisting);
            try {
                gen.runGenerator(dataNode.getDataSource());
            } catch (Exception e) {
                throw new CayenneRuntimeException(e);
            }
        }
    }

    /**
     * Returns all the table names in database.
     *
     * @param dataNode node whose tables should be returned
     * @return a map with all table names
     * @throws SQLException thrown on error occured accessing the database
     */
    protected Map<String, Boolean> getNameTablesInDB(DataNode dataNode)
            throws SQLException {
        String tableLabel = dataNode.getAdapter().tableTypeForTable();
        Map<String, Boolean> nameTables = new HashMap<>();
        Connection con = dataNode.getDataSource().getConnection();

        try {
            ResultSet rs = con.getMetaData().getTables(null, null, "%", new String[]{
                    tableLabel
            });

            try {

                while (rs.next()) {
                    String name = rs.getString("TABLE_NAME");
                    nameTables.put(name, false);
                }
            } finally {
                rs.close();
            }

        } finally {

            con.close();
        }
        return nameTables;
    }
}
