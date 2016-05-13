import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Администратор on 08.02.2016.
 */
public class RecordTransaction {
    Date dataRecTrn;
    Integer typeTrn;
    Integer sumTrn;
    String commentTrn;

    public RecordTransaction(Date dataRecTrn,  Integer typeTrn, Integer sumTrn, String commentTrn) {
        this.dataRecTrn = dataRecTrn;
        this.typeTrn = typeTrn;
        this.sumTrn = sumTrn;
        this.commentTrn = commentTrn;
    }

    public Date formatDataSql(Date data){
        SimpleDateFormat formatData = new SimpleDateFormat("dd-MM-yyyy");
        formatData.format(data);
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        return sqlDate;
    }

    public Date getDataRecTrn() {
        return formatDataSql(dataRecTrn);
    }

    public void setDataRecTrn(Date dataRecTrn) {
        this.dataRecTrn = dataRecTrn;
    }

    public Integer getTypeTrn() {
        return typeTrn;
    }

    public void setTypeTrn(Integer typeTrn) {
        this.typeTrn = typeTrn;
    }

    public Integer getSumTrn() {
        return sumTrn;
    }

    public void setSumTrn(Integer sumTrn) {
        this.sumTrn = sumTrn;
    }

    public String getCommentTrn() {
        return commentTrn;
    }

    public void setCommentTrn(String commentTrn) {
        this.commentTrn = commentTrn;
    }
}