package unidue.rc.model.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

import unidue.rc.model.Entry;
import unidue.rc.model.Resource;

/**
 * Class _File was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _File extends CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String MODIFIED_PROPERTY = "modified";
    public static final String ENTRY_PROPERTY = "entry";
    public static final String RESOURCE_PROPERTY = "resource";

    public static final String ID_PK_COLUMN = "id";

    public void setDescription(String description) {
        writeProperty(DESCRIPTION_PROPERTY, description);
    }
    public String getDescription() {
        return (String)readProperty(DESCRIPTION_PROPERTY);
    }

    public void setModified(Date modified) {
        writeProperty(MODIFIED_PROPERTY, modified);
    }
    public Date getModified() {
        return (Date)readProperty(MODIFIED_PROPERTY);
    }

    public void setEntry(Entry entry) {
        setToOneTarget(ENTRY_PROPERTY, entry, true);
    }

    public Entry getEntry() {
        return (Entry)readProperty(ENTRY_PROPERTY);
    }


    public void setResource(Resource resource) {
        setToOneTarget(RESOURCE_PROPERTY, resource, true);
    }

    public Resource getResource() {
        return (Resource)readProperty(RESOURCE_PROPERTY);
    }


}
