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
package unidue.rc.migration.legacymodel;


import unidue.rc.migration.MigrationException;
import unidue.rc.model.Entry;
import unidue.rc.model.ReserveCollection;

/**
 * @author Nils Verheyen
 * @since 07.04.14 15:42
 */
public interface MigrationVisitable {

    void migrateEntryValue(MigrationVisitor visitor, ReserveCollection collection, Entry entry, EntryLocal entryLocal, String derivateID) throws MigrationException;
}
