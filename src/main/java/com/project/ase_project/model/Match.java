package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadataDto_id", referencedColumnName = "id")
    private MetadataDto metadataDto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infoDto_id", referencedColumnName = "id")
    private InfoDto infoDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetadataDto getMetadataDto() {
        return metadataDto;
    }

    public void setMetadataDto(MetadataDto metadataDto) {
        this.metadataDto = metadataDto;
    }

    public InfoDto getInfoDto() {
        return infoDto;
    }

    public void setInfoDto(InfoDto infoDto) {
        this.infoDto = infoDto;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", metadataDto=" + metadataDto +
                ", infoDto=" + infoDto +
                '}';
    }
}
