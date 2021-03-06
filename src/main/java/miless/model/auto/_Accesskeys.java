package miless.model.auto;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _Accesskeys was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Accesskeys extends CayenneDataObject {

    public static final String READKEY_PROPERTY = "readkey";
    public static final String WRITEKEY_PROPERTY = "writekey";

    public static final String DOCID_PK_COLUMN = "docid";

    public void setReadkey(String readkey) {
        writeProperty(READKEY_PROPERTY, readkey);
    }
    public String getReadkey() {
        return (String)readProperty(READKEY_PROPERTY);
    }

    public void setWritekey(String writekey) {
        writeProperty(WRITEKEY_PROPERTY, writekey);
    }
    public String getWritekey() {
        return (String)readProperty(WRITEKEY_PROPERTY);
    }

}
