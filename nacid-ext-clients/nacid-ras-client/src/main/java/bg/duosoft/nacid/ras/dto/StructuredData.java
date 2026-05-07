package bg.duosoft.nacid.ras.dto;

/**
 * User: ggeorgiev
 * Date: 22.10.2019 г.
 * Time: 18:49
 */

public class StructuredData {
    private Person person;
    private AcademicDegree academicDegree;
    private FileStorageResponse certificateFile;
    private FileStorageResponse dissertationFile;
    private FileStorageResponse summaryFile;
    private FileStorageResponse diplomaFile;
//    private String lotId = null;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public FileStorageResponse getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(FileStorageResponse certificateFile) {
        this.certificateFile = certificateFile;
    }

    public FileStorageResponse getDissertationFile() {
        return dissertationFile;
    }

    public void setDissertationFile(FileStorageResponse dissertationFile) {
        this.dissertationFile = dissertationFile;
    }

    public FileStorageResponse getSummaryFile() {
        return summaryFile;
    }

    public void setSummaryFile(FileStorageResponse summaryFile) {
        this.summaryFile = summaryFile;
    }

    public FileStorageResponse getDiplomaFile() {
        return diplomaFile;
    }

    public void setDiplomaFile(FileStorageResponse diplomaFile) {
        this.diplomaFile = diplomaFile;
    }
}