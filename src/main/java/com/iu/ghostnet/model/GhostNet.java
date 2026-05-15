package com.iu.ghostnet.model;

import jakarta.persistence.*;

@Entity
public class GhostNet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private String size;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person reportingPerson; // Kann null sein (anonym)

    @ManyToOne(cascade = CascadeType.ALL)
    private Person salvagingPerson; // Maximal eine Person zugeordnet 

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Person getReportingPerson() { return reportingPerson; }
    public void setReportingPerson(Person reportingPerson) { this.reportingPerson = reportingPerson; }
    public Person getSalvagingPerson() { return salvagingPerson; }
    public void setSalvagingPerson(Person salvagingPerson) { this.salvagingPerson = salvagingPerson; }
}