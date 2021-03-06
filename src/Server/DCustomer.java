package Server;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public class DCustomer implements SQLData, Serializable  {
    private BigDecimal csid;
    private BigDecimal custid;
    private String name;
    private String place;
    private String state;
    private String country;

    //SQL DATA
    private String sqlUdt;

    public DCustomer(BigDecimal csid, BigDecimal custid, String name, String place, String state, String country) {
        this.csid = csid;
        this.custid = custid;
        this.name = name;
        this.place = place;
        this.state = state;
        this.country = country;
    }

    public BigDecimal getCsid() {
        return csid;
    }

    public void setCsid(BigDecimal csid) {
        this.csid = csid;
    }

    public BigDecimal getCustid() {
        return custid;
    }

    public void setCustid(BigDecimal custid) {
        this.custid = custid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CSID: "+csid)
                .append("CUSTID: "+custid)
                .append("Name: " + name)
                .append("Place: "+place)
                .append("State: "+state)
                .append("Country: "+country);
        return sb.toString();
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlUdt;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        sqlUdt = typeName;
        custid = stream.readBigDecimal();
        csid = stream.readBigDecimal();
        name = stream.readString();
        country = stream.readString();
        place = stream.readNString();
        state = stream.readNString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeBigDecimal(csid);
        stream.writeBigDecimal(custid);
        stream.writeString(name);
        stream.writeString(country);
        stream.writeString(place);
        stream.writeString(state);
    }
}
