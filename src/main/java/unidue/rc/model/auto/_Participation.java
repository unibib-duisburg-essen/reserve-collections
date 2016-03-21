package unidue.rc.model.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

import unidue.rc.model.ReserveCollection;
import unidue.rc.model.Role;

/**
 * Class _Participation was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Participation extends CayenneDataObject {

    public static final String ACCESS_KEY_PROPERTY = "accessKey";
    public static final String COLLECTION_ID_PROPERTY = "collectionID";
    public static final String END_DATE_PROPERTY = "endDate";
    public static final String START_DATE_PROPERTY = "startDate";
    public static final String USER_ID_PROPERTY = "userId";
    public static final String RESERVE_COLLECTION_PROPERTY = "reserveCollection";
    public static final String ROLE_PROPERTY = "role";

    public static final String ID_PK_COLUMN = "id";

    public void setAccessKey(String accessKey) {
        writeProperty(ACCESS_KEY_PROPERTY, accessKey);
    }
    public String getAccessKey() {
        return (String)readProperty(ACCESS_KEY_PROPERTY);
    }

    public void setCollectionID(Integer collectionID) {
        writeProperty(COLLECTION_ID_PROPERTY, collectionID);
    }
    public Integer getCollectionID() {
        return (Integer)readProperty(COLLECTION_ID_PROPERTY);
    }

    public void setEndDate(Date endDate) {
        writeProperty(END_DATE_PROPERTY, endDate);
    }
    public Date getEndDate() {
        return (Date)readProperty(END_DATE_PROPERTY);
    }

    public void setStartDate(Date startDate) {
        writeProperty(START_DATE_PROPERTY, startDate);
    }
    public Date getStartDate() {
        return (Date)readProperty(START_DATE_PROPERTY);
    }

    public void setUserId(Integer userId) {
        writeProperty(USER_ID_PROPERTY, userId);
    }
    public Integer getUserId() {
        return (Integer)readProperty(USER_ID_PROPERTY);
    }

    public void setReserveCollection(ReserveCollection reserveCollection) {
        setToOneTarget(RESERVE_COLLECTION_PROPERTY, reserveCollection, true);
    }

    public ReserveCollection getReserveCollection() {
        return (ReserveCollection)readProperty(RESERVE_COLLECTION_PROPERTY);
    }


    public void setRole(Role role) {
        setToOneTarget(ROLE_PROPERTY, role, true);
    }

    public Role getRole() {
        return (Role)readProperty(ROLE_PROPERTY);
    }


}
