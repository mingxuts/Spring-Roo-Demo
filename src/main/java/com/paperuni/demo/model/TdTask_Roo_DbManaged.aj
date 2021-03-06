// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.paperuni.demo.model;

import com.paperuni.demo.model.TdMessage;
import com.paperuni.demo.model.TdOrder;
import com.paperuni.demo.model.TdTask;
import com.paperuni.demo.model.TdUserinfo;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect TdTask_Roo_DbManaged {
    
    @OneToMany(mappedBy = "taskId")
    private Set<TdMessage> TdTask.tdMessages;
    
    @OneToMany(mappedBy = "taskId")
    private Set<TdOrder> TdTask.tdOrders;
    
    @ManyToOne
    @JoinColumn(name = "WriterID", referencedColumnName = "ID")
    private TdUserinfo TdTask.writerId;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "ID")
    private TdUserinfo TdTask.customerId;
    
    @Column(name = "AllowAllSubject")
    private Boolean TdTask.allowAllSubject;
    
    @Column(name = "AmountDue", precision = 8, scale = 2)
    private BigDecimal TdTask.amountDue;
    
    @Column(name = "ChargeWriter", precision = 8, scale = 2)
    private BigDecimal TdTask.chargeWriter;
    
    @Column(name = "Commission", precision = 8, scale = 2)
    private BigDecimal TdTask.commission;
    
    @Column(name = "Coupon", length = 35)
    private String TdTask.coupon;
    
    @Column(name = "CourseLevel", length = 10)
    private String TdTask.courseLevel;
    
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar TdTask.createDate;
    
    @Column(name = "DeadLine")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar TdTask.deadLine;
    
    @Column(name = "FeedBack", length = 2)
    private String TdTask.feedBack;
    
    @Column(name = "FeedBackDescription", length = 255)
    private String TdTask.feedBackDescription;
    
    @Column(name = "File")
    private byte[] TdTask.file;
    
    @Column(name = "FileContentType", length = 50)
    private String TdTask.fileContentType;
    
    @Column(name = "Format", length = 30)
    private String TdTask.format;
    
    @Column(name = "FullPrice", precision = 8, scale = 2)
    private BigDecimal TdTask.fullPrice;
    
    @Column(name = "IncludeFigure", length = 30)
    private String TdTask.includeFigure;
    
    @Column(name = "Note", length = 255)
    private String TdTask.note;
    
    @Column(name = "OrderID")
    @NotNull
    private Integer TdTask.orderId;
    
    @Column(name = "Reduced", precision = 8, scale = 2)
    private BigDecimal TdTask.reduced;
    
    @Column(name = "Referencing", length = 30)
    private String TdTask.referencing;
    
    @Column(name = "SourcesCount")
    private Short TdTask.sourcesCount;
    
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar TdTask.startDate;
    
    @Column(name = "Status", length = 88)
    private String TdTask.status;
    
    @Column(name = "SubjectID")
    private Integer TdTask.subjectId;
    
    @Column(name = "WordCount")
    private Integer TdTask.wordCount;
    
    @Column(name = "FileName", length = 70)
    private String TdTask.fileName;
    
    @Column(name = "FileSize")
    private Integer TdTask.fileSize;
    
    public Set<TdMessage> TdTask.getTdMessages() {
        return tdMessages;
    }
    
    public void TdTask.setTdMessages(Set<TdMessage> tdMessages) {
        this.tdMessages = tdMessages;
    }
    
    public Set<TdOrder> TdTask.getTdOrders() {
        return tdOrders;
    }
    
    public void TdTask.setTdOrders(Set<TdOrder> tdOrders) {
        this.tdOrders = tdOrders;
    }
    
    public TdUserinfo TdTask.getWriterId() {
        return writerId;
    }
    
    public void TdTask.setWriterId(TdUserinfo writerId) {
        this.writerId = writerId;
    }
    
    public TdUserinfo TdTask.getCustomerId() {
        return customerId;
    }
    
    public void TdTask.setCustomerId(TdUserinfo customerId) {
        this.customerId = customerId;
    }
    
    public Boolean TdTask.getAllowAllSubject() {
        return allowAllSubject;
    }
    
    public void TdTask.setAllowAllSubject(Boolean allowAllSubject) {
        this.allowAllSubject = allowAllSubject;
    }
    
    public BigDecimal TdTask.getAmountDue() {
        return amountDue;
    }
    
    public void TdTask.setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }
    
    public BigDecimal TdTask.getChargeWriter() {
        return chargeWriter;
    }
    
    public void TdTask.setChargeWriter(BigDecimal chargeWriter) {
        this.chargeWriter = chargeWriter;
    }
    
    public BigDecimal TdTask.getCommission() {
        return commission;
    }
    
    public void TdTask.setCommission(BigDecimal commission) {
        this.commission = commission;
    }
    
    public String TdTask.getCoupon() {
        return coupon;
    }
    
    public void TdTask.setCoupon(String coupon) {
        this.coupon = coupon;
    }
    
    public String TdTask.getCourseLevel() {
        return courseLevel;
    }
    
    public void TdTask.setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }
    
    public Calendar TdTask.getCreateDate() {
        return createDate;
    }
    
    public void TdTask.setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }
    
    public Calendar TdTask.getDeadLine() {
        return deadLine;
    }
    
    public void TdTask.setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }
    
    public String TdTask.getFeedBack() {
        return feedBack;
    }
    
    public void TdTask.setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
    
    public String TdTask.getFeedBackDescription() {
        return feedBackDescription;
    }
    
    public void TdTask.setFeedBackDescription(String feedBackDescription) {
        this.feedBackDescription = feedBackDescription;
    }
    
    public byte[] TdTask.getFile() {
        return file;
    }
    
    public void TdTask.setFile(byte[] file) {
        this.file = file;
    }
    
    public String TdTask.getFileContentType() {
        return fileContentType;
    }
    
    public void TdTask.setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    
    public String TdTask.getFormat() {
        return format;
    }
    
    public void TdTask.setFormat(String format) {
        this.format = format;
    }
    
    public BigDecimal TdTask.getFullPrice() {
        return fullPrice;
    }
    
    public void TdTask.setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }
    
    public String TdTask.getIncludeFigure() {
        return includeFigure;
    }
    
    public void TdTask.setIncludeFigure(String includeFigure) {
        this.includeFigure = includeFigure;
    }
    
    public String TdTask.getNote() {
        return note;
    }
    
    public void TdTask.setNote(String note) {
        this.note = note;
    }
    
    public Integer TdTask.getOrderId() {
        return orderId;
    }
    
    public void TdTask.setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public BigDecimal TdTask.getReduced() {
        return reduced;
    }
    
    public void TdTask.setReduced(BigDecimal reduced) {
        this.reduced = reduced;
    }
    
    public String TdTask.getReferencing() {
        return referencing;
    }
    
    public void TdTask.setReferencing(String referencing) {
        this.referencing = referencing;
    }
    
    public Short TdTask.getSourcesCount() {
        return sourcesCount;
    }
    
    public void TdTask.setSourcesCount(Short sourcesCount) {
        this.sourcesCount = sourcesCount;
    }
    
    public Calendar TdTask.getStartDate() {
        return startDate;
    }
    
    public void TdTask.setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    
    public String TdTask.getStatus() {
        return status;
    }
    
    public void TdTask.setStatus(String status) {
        this.status = status;
    }
    
    public Integer TdTask.getSubjectId() {
        return subjectId;
    }
    
    public void TdTask.setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    public Integer TdTask.getWordCount() {
        return wordCount;
    }
    
    public void TdTask.setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
    
    public String TdTask.getFileName() {
        return fileName;
    }
    
    public void TdTask.setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public Integer TdTask.getFileSize() {
        return fileSize;
    }
    
    public void TdTask.setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    
}
