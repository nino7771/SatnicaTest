package mojaSatnica;

public class Calculate extends Model {

	static int tempTotalSati = 0;      //total sati
	static int tempTotalMinuti = 0;    //total minuti		
	static double plata=0; 				//ukupno plata
	static double totalBonus1;			//ukupno bonus
	boolean provera=false;             //proverava da li je pritisnuto dugme remove
	
	
	
	//racuna zbir ukupno sati jedne kolumne
	public int ukupnoSati() {
	int satiUkupno = 0;
	
	if(getSatiDolazak() <= getSatiOdlazak())
		satiUkupno = getSatiOdlazak() - getSatiDolazak();
	else if(getSatiDolazak() > getSatiOdlazak())
		satiUkupno = 24-(getSatiDolazak() - getSatiOdlazak());
	//ukoliko su minuti odlaska manji od minuta dolaska onda sati ukupno umanjuju za jedan sat
	if(getMinutiOdlazak()<getMinutiDolazak())
		satiUkupno--;
	
	return satiUkupno;
	
	}//ukupnoSati()
	
	
	//racuna ukupno zbir minuta jednog reda
	public int ukupnoMinuti() {
	int minutiUkupno = 0;
	
	if(getMinutiOdlazak()>=getMinutiDolazak())
		minutiUkupno=getMinutiOdlazak()-getMinutiDolazak();
	
	else if(getMinutiOdlazak()<getMinutiDolazak()) 
		minutiUkupno=(getMinutiOdlazak()-getMinutiDolazak())+60;
	return minutiUkupno;
	}//ukupnoMinuti()
	
	
	//zbir ukupno sati - total iz svih redova
	public int totalSati() {
		if(provera==false)  				//provera dugme remove nije pritisnuto
			tempTotalSati+=getUkupnoSati();
		else if (provera==true)				//provera dugme remove je pritisnuto
			tempTotalSati-=getUkupnoSati();
			
		return tempTotalSati;
	}//totalSati()
	
	
	//zbir svih ukupno minuta total iz svih redova
	public int totalMinuti() {
		if(provera == false)						//provera dugme remove nije pritisnuto
			tempTotalMinuti += getUkupnoMinuti();
		else if(provera == true)					//provera dugme remove je pritisnuto
			tempTotalMinuti -= getUkupnoMinuti();
			
			
		//ako je provera true ili false
		if(tempTotalMinuti >= 60) {				//ako su total minuti >= od 60 onda total minuti - 60  i total sati +1
			tempTotalMinuti = tempTotalMinuti - 60;
			tempTotalSati++;						
		}
		else if(tempTotalMinuti < 0) {			//ako su total minuti < od 0 onda total minuti + 60 total sati -1
			tempTotalMinuti = tempTotalMinuti + 60;
			--tempTotalSati;
		}
		
		return tempTotalMinuti;
	}//totalMinuti(
	
	
	//obracun vanrednih sati
	public int vanredniSati() {
		
		int tempVanredniSati=0;
	
		if(tempTotalSati>getRedovniSati()) 							//ako su total sati > od redovnih sati 
				tempVanredniSati+=tempTotalSati-getRedovniSati();
		
		else if(tempTotalSati<=getRedovniSati()){					//ako su total sati <= od redovnih sati vanredni sati = 0
				tempVanredniSati=0;
				}
		return tempVanredniSati;	
	}//vanredniSati()
	
	
	
	//obracun plate 
	public double obracunPlate() {
		
		double tempKoeficijent = getNormalniKoeficijent();		//koeficijent redovni sati
		double tempExtraKoeficijent = getExtraKoeficijent();	//koeficijent vanredni sati
		double tempVanredniSati = vanredniSati();				//vanredni sati
		int tempRedovniSati = getRedovniSati();					//redovni sati
		int totalSati = tempTotalSati;							//total sati
		double tempBonus= getBonus();
		
		
		if(provera == false) {								//provera remove dugme  nije pritisnuto
			if(tempRedovniSati >= totalSati)					//ako su redovni sati >= od total sati
				plata = tempKoeficijent * totalSati;
		
			else if(tempRedovniSati < totalSati) 				//ako su redovni sati < od total sati
				plata = tempVanredniSati * tempExtraKoeficijent + tempKoeficijent * tempRedovniSati;
		}
		else if(provera == true) {							//provera remove dugme  je pritisnuto
			
			if(tempRedovniSati >= totalSati) {				//ako su redovni sati >= od total sati
				
				plata = totalSati * tempKoeficijent;
			}
			else if(tempRedovniSati < totalSati) {			//ako su redovni sati < od total sati
				
				plata = tempVanredniSati * tempExtraKoeficijent + tempKoeficijent * tempRedovniSati;
			}
			
		}
		if(tempBonus > 0)
			plata += totalBonus1;
			return plata;
		
		}//obracunPlate()
	
	//Obracun ukupnog bonusa
	double obracunTotalBonusa() {
		if(provera == false)
			totalBonus1 += getBonus();
		
		else if(provera == true) 
			totalBonus1 -= getBonus();
			
		return totalBonus1;
	}
	
	}//class Calculate
