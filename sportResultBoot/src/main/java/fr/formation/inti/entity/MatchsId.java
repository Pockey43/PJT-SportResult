//package fr.formation.inti.entity;
//// Generated 18 fevr. 2022 e 08:19:10 by Hibernate Tools 3.6.0.Final
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//
///**
// * MatchsId generated by hbm2java
// */
//@Embeddable
//public class MatchsId implements java.io.Serializable {
//
//    private int idMatch;
//
//    private int equipesExterieursIdequipes;
//     private int equipesDomicilesIdequipes;
//
//    public MatchsId() {
//    }
//
//    public MatchsId(int idMatch, int equipesExterieursIdequipes, int equipesDomicilesIdequipes) {
//       this.idMatch = idMatch;
//       this.equipesExterieursIdequipes = equipesExterieursIdequipes;
//       this.equipesDomicilesIdequipes = equipesDomicilesIdequipes;
//    }
//
//    @Column(name = "idMatch", nullable = false)
//    public int getIdMatch() {
//        return this.idMatch;
//    }
//
//    public void setIdMatch(int idMatch) {
//        this.idMatch = idMatch;
//    }
//
//    @Column(name="équipes_extérieurs_idéquipes", nullable=false)
//    public int getEquipesExterieursIdequipes() {
//        return this.equipesExterieursIdequipes;
//    }
//
//    public void setEquipesExterieursIdequipes(int equipesExterieursIdequipes) {
//        this.equipesExterieursIdequipes = equipesExterieursIdequipes;
//    }
//
//    @Column(name="équipes_domiciles_idéquipes", nullable=false)
//    public int getEquipesDomicilesIdequipes() {
//        return this.equipesDomicilesIdequipes;
//    }
//
//    public void setEquipesDomicilesIdequipes(int equipesDomicilesIdequipes) {
//        this.equipesDomicilesIdequipes = equipesDomicilesIdequipes;
//    }
//    
//    public boolean equals(Object other) {
//        if ( (this == other ) ) return true;
//        if ( (other == null ) ) return false;
//        if ( !(other instanceof MatchsId) ) return false;
//        MatchsId castOther = ( MatchsId ) other; 
//
//        return (this.getIdMatch()==castOther.getIdMatch())
//&& (this.getEquipesExterieursIdequipes()==castOther.getEquipesExterieursIdequipes())
//&& (this.getEquipesDomicilesIdequipes()==castOther.getEquipesDomicilesIdequipes());
//  }
//
//   public int hashCode() {
//        int result = 17;
//
//        result = 37 * result + this.getIdMatch();
//        result = 37 * result + this.getEquipesExterieursIdequipes();
//        result = 37 * result + this.getEquipesDomicilesIdequipes();
//        return result;
//  }
//
//}
