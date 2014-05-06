package Recherche;
import connexion.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;

public class Recherche {

	private String nomtable;
	private Connexion c;
	
    /**
     * ArrayList public pour les requêtes de sélection
     */
	
    public ArrayList<String> requetes = new ArrayList<String>();

    /**
     * constructeur
     */
    public Recherche(Connexion c)
    {
    	this.c=c;
    }
    
    /** methode pour executer une requete*/
    
    public ArrayList resultatrequete(String requete) throws SQLException
    {
    	ArrayList<String> liste = new ArrayList<String>();
    	liste=c.remplirChampsRequete(requete);
    	return liste;
    }
    
    /** methode pour chercher un docteur ou un infirmier avec les champs des tables docteur+employe ou infirmier+employe */
    public ArrayList chercherdocinf(ArrayList donnee,int type ) throws SQLException {
           
          /** ArrayList public pour les noms des champs*/
           ArrayList<String> champs1 = new ArrayList<String>();
           ArrayList<String> champs = new ArrayList<String>(); 

        
            /** determiner le type de donnees qu'on cherche */
                
           if(type==0)     
               nomtable="docteur"; 
           champs=c.remplirChampsTable("docteur");
           champs1=c.remplirChampsTable("employe");
           champs.addAll(champs1);
                       
           if(type==1)     
               nomtable="infirmier";
           champs=c.remplirChampsTable("infirmier");
           champs1=c.remplirChampsTable("employe");
           champs.addAll(champs1);
                         
            
 
      //creer deux nouvelleS listes pour mettre les donnees sans espaces
       
       ArrayList<String> donneef;
       donneef = new ArrayList<String>();
      
       ArrayList<String> champsf;
       champsf = new ArrayList<String>();
        
       //eliminer les espaces dans les donnees
       for(int i=0;i<donnee.size();i++)
       {
           if(!" ".equals(donnee.get(i)))
           {
               donneef.add((String) donnee.get(i));
               champsf.add(champs.get(i));
           }
       }
        
        
        
        //determiner les conditions
        
        String condition =" ";
        for(int i=0;i<donneef.size();i++)
        {
           condition=condition.concat(champsf.get(i)).concat("=").concat(donneef.get(i)).concat(" AND ");
        }
        condition=condition.substring(0,condition.length()-5);
        
        /** requete finale */
        String sql="SELECT "+nomtable+"* FROM "+nomtable+" n, employe e WHERE e.numero=n.numero AND "+condition+";";
       
       /**creation d'une liste pour mettre le resultat de la requete*/
       ArrayList<String> liste;
       liste = new ArrayList<String>();
       
       /**récupérer le résultat de la requête*/

           liste=c.remplirChampsRequete(sql);
      
         
      return liste;  
    }
    
	
	
	/** methode pour chercher tous les types de donnees avec tous les champs initials des tables*/
    public ArrayList rechercher(ArrayList donnee,int type) throws SQLException
    {
        /** ArrayList public pour les noms des champs*/
       ArrayList<String> champs = new ArrayList<String>(); 
        
      
       
        
        //determiner le type de donnees qu'on cherche
            switch(type)
           { case 0: nomtable="docteur";
                     champs=c.remplirChampsTable("docteur");
                     
                     break;
             case 1: nomtable="infirmier";
                     champs=c.remplirChampsTable("infirmier");
                     
                     break;
             case 2: nomtable="employe";
                     champs=c.remplirChampsTable("employe");
                     
                     break;
             case 3: nomtable="malade";
                     champs=c.remplirChampsTable("malade");
                   
                     break;
             case 4: nomtable="service";
                     champs=c.remplirChampsTable("service");
                     
                     break;
             case 5: nomtable="chambre";
                     champs=c.remplirChampsTable("chambre");
                     
                     break;
             case 6: nomtable="hospitalisation";
                     champs=c.remplirChampsTable("hospitalisation");

                     break;
             case 7: nomtable="soigne";
                     champs=c.remplirChampsTable("soigne");

                     break;
              
           }
        
   
  //creer deux nouvelleS listes pour mettre les donnees sans espaces
   
   ArrayList<String> donneef;
   donneef = new ArrayList<String>();
  
   ArrayList<String> champsf;
   champsf = new ArrayList<String>();
    
   //eliminer les espaces dans les donnees
   for(int i=0;i<donnee.size();i++)
   {
       if(!" ".equals(donnee.get(i)))
       {
           donneef.add((String) donnee.get(i));
           champsf.add(champs.get(i));
       }
   }
    
    
    
    //determiner les conditions
    
    String condition =" ";
    for(int i=0;i<donneef.size();i++)
    {
       condition=condition.concat(champsf.get(i)).concat("=").concat(donneef.get(i)).concat(" AND ");
    }
    condition=condition.substring(0,condition.length()-5);
    
   

    /** requete finale */
    
    String sql="SELECT* FROM "+nomtable+" WHERE"+condition+";";
    
   /**creation d'une liste pour mettre le resultat de la requete*/
   ArrayList<String> liste;
   liste = new ArrayList<String>();
    
    
    /** resultat de la requete*/
   liste=c.remplirChampsRequete(sql);
   
            
        
            
       return liste;        
    }
    


    
    
    
    /**
     * Méthode privée qui ajoute la requete de selection en parametre dans son ArrayList
     */
    private void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */
    private void remplirRequetes() {
        ajouterRequete("SELECT prenom, nom FROM malade WHERE mutuelle='MAAF' ORDER BY nom;");
        ajouterRequete("SELECT prenom, nom FROM infirmier WHERE rotation='NUIT' ORDER BY nom;");
        ajouterRequete("SELECT s.nom,s.batiment,e.prenom,e.nom,d.specialite FROM service s,employe e, docteur d WHERE e.numero=s.directeur AND e.numero=d.numero;");
        ajouterRequete("SELECT c.no_chambre,h.lit,s.nom,m.prenom,m.nom,m.mutuelle FROM chambre c,hospitalisation h, service s, malade m WHERE c.no_chambre=h.no_chambre AND c.code_service=s.code AND m.numero=h.no_malade AND h.code_service=s.code AND m.mutuelle LIKE 'MN%' AND s.batiment='B';");
        ajouterRequete("SELECT code_service, AVG(salaire) moyenne_des_salaires FROM infirmier GROUP BY code_service;");
        ajouterRequete("SELECT code_service,AVG(nb_lits) nb_moyen_de_lits FROM chambre c,service s WHERE c.code_service=s.code AND s.batiment='A' GROUP BY code_service;");
        ajouterRequete("SELECT m.nom, m.prenom, COUNT(DISTINCT no_docteur) nb_soignant,COUNT(DISTINCT d.specialite) nb_specialite FROM malade m,soigne s,docteur d WHERE m.numero=s.no_malade AND d.numero=s.no_docteur GROUP BY m.nom HAVING COUNT(s.no_malade)>3;");
        ajouterRequete("SELECT s.nom,COUNT(DISTINCT i.numero)/COUNT(DISTINCT h.no_malade) rapport_i_sur_m FROM infirmier i,hospitalisation h, service s WHERE i.code_service=h.code_service AND s.code=i.code_service GROUP BY s.nom;");
        ajouterRequete("SELECT e.prenom,e.nom FROM employe e WHERE EXISTS(SELECT* FROM docteur d,soigne s, hospitalisation h WHERE d.numero=s.no_docteur AND s.no_malade=h.no_malade AND d.numero=e.numero) ORDER BY e.nom;");
        ajouterRequete("SELECT e.prenom,e.nom FROM employe e,docteur d1 WHERE e.numero=d1.numero AND d1.numero NOT IN(SELECT d2.numero FROM docteur d2 WHERE EXISTS(SELECT* FROM docteur d,soigne s, hospitalisation h WHERE d.numero=s.no_numero AND s.no_malade=h.no_malade AND d.numero=d2.numero))ORDER BY e.nom;");
    
    }
	
	
	
	
}


