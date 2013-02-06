#include <iostream>
#include <string>

extern "C" {

	__declspec(dllexport) const char* getName(){
		return "LocsimDesmMiddleware";
	}

	__declspec(dllexport) const char* getDescription(){
		return "This DLL contains the implementation of the communication between the system components DESM-Middleware and LOCSIM.";
	}

	__declspec(dllexport) int initialize(){
		std::cout << "initialize" << std::endl;
		return 0;
	}

	__declspec(dllexport) int release(){
		std::cout << "releasing" << std::endl;
		return 0;
	}

	/**
	* Behandlung von Versionskonflikten der DLL.
	**/
	__declspec(dllexport) const char* stw_getVersion(){
		return "0.1";
	}

	/**
	* Beim Laden der DLL library, wird der Netzwerkserver durch diese Funktion gestartet.
	**/
	__declspec(dllexport) int stw_onLoadLibrary(){
		std::cout << "stw_onloadLibrary()" << std::endl;
		return 0;
	}

	/**
	* Wenn der LOCSIM die Simulation stoppt wird die DLL entladen und der der Netzwerkserver durch diese Funktion
	* geschlossen und gestoppt. Damit wird die Verbindung zwischen Server und Client ordnungsgemäss geschlossen.
	**/
	__declspec(dllexport) int stw_onUnloadLibrary(){
		std::cout << "stw_onunloadLibrary()" << std::endl;
		return 0;
	}

	/**
	* Wenn der LOCSIM die Simulation stoppt wird die DLL entladen und der der Netzwerkserver durch diese Funktion
	* geschlossen und gestoppt. Damit wird die Verbindung zwischen Server und Client ordnungsgemäss geschlossen.
	**/
	__declspec(dllexport) int stw_init(){
		std::cout << "stw_init()" << std::endl;
		return 0;
	}

	/**
	* markiert Simulation Start
	* impliziert das Event „Strecke neu laden“
	* es werden ab jetzt mögliche „defines“ zum Verarbeiten erwartet
	**/
	__declspec(dllexport) int stw_ready(){
		std::cout << "stw_ready()" << std::endl;
		return 0;
	}

	/**
	* Definition von zu Hauptgleis parallelen Gleisabschnitten
	* Das Hauptgleis ist die Standardfahrstrasse der Simulation.
	**/
	__declspec(dllexport) int define_track (int gleisId, double von, double bis, float abstand, char* name){
		std::cout << "define_track (int gleisId, double von, double bis, float abstand, char* name)" << std::endl;
		return 0;
	}

	/**
	* Definition von Verbindungen zwischen parallelen Gleisabschnitten
	* weiche1_id, weiche2_id = ID der Weiche (eindeutige locsim-interne Nummerierung), wenn 0: keine Weiche (wenn z.B. connection2 in gleis2 übergeht)
	* pro Längsposition dürfen max. 20 verschiedene Gleise definiert sein (ID= 1…20) 
	**/
	__declspec(dllexport) int define_trackConnection (int gleis1, int gleis2, double von, double bis, char* name, int weiche1_id, int weiche2_id){
		std::cout << "define_trackConnection (int gleis1, int gleis2, double von, double bis, char* name, int weiche1_id, int weiche2_id)" << std::endl;
		return 0;
	}

	/**
	* wird pro Signal aufgerufen
	**/
	__declspec(dllexport) int define_signal (int signal_id, int gleis_id, double position, int typ, float hoehe, float distanz, char* name){
		std::cout << "define_signal (int signal_id, int gleis_id, double position, int typ, float hoehe, float distanz, char* name)" << std::endl;
		return 0;
	}

	/**
	* definiert eine Balise auf einem bestimmten Gleis an einer bestimmten Position
	**/
	__declspec(dllexport) int define_balise (int gleis_id, double position, int balise_id){
		std::cout << "define_balise (int gleis_id, double position, int balise_id)" << std::endl;
		return 0;
	}

	/**
	* Ist in den locsim-Streckendaten bis jetzt nicht drin, könnte aber dazugefügt werden
	**/
	__declspec(dllexport) int define_isolierstoss (int gleis, double position){
		std::cout << "define_isolierstoss (int gleis, double position)" << std::endl;
		return 0;
	}

	/**
	* Funktion: Locsim fragt DLL, welche events vom Stellwerk vorhanden sind (DLL cached)
	* jedes Event führt zum Aufruf einer der nachstehenden Funktionen
	* stw_get_event(3, typeList(1,1,2), idList(63, 32 , 765) )
	*
	* Beispiel:
	*	int num;
	*	int* type_list;
	*	int* id_list;
	*	stw_getEvents(&number, &type_list, &id_list);
	*
	**/	
	__declspec(dllexport) int stw_getEvents(int* number, int** type_list, int** id_list){
		std::cout << "stw_getEvents(int* number, int** type_list, int** id_list)" << std::endl;
		return 0;
	}

	/**
	* gibt die Stellung eines bestimmten Signals zurück
	**/
	__declspec(dllexport) int stw_getSignal (int signal_id, int* stellung){
		std::cout << "stw_getSignal (int signal_id, int* stellung)" << std::endl;
		return 0;
	}

	/**
	* gibt eine Stellung und das Protokoll einer bestimmten Balise zurück
	**/
	__declspec(dllexport) int stw_getBalise (int balise_id, int* stellung, char** protokoll){
		std::cout << "get_balise (int balise_id, int* stellung, char** protokoll)" << std::endl;
		return 0;
	}

	/**
	* gibt die Stellung einer bestimmten Weiche zurück
	**/
	__declspec(dllexport) int stw_getWeiche (int weiche_id, int* gleis_id){
		std::cout << "get_weiche (int weiche_id, int* gleis_id)" << std::endl;
		return 0;
	}

	/**
	* Übergibt pos1 – gleis1 – pos2 – gleis2 – pos3 - … des Zuges train an stellwerk.dll wenn die Zugspitze oder der Zugschluss einen Isolierstoss überfährt
	* Was bedeutet in diesem Fall die Position?
	* Anzahl pos = Anzahl gleis +1
	**/
	__declspec(dllexport) int define_trainposition(int train, int direction, double** positionList, int** gleisList){
		std::cout << "define_trainposition(int train, int direction, double** positionList, int** gleisList)" << std::endl;
		return 0;
	}

	/**
	* Speicher Allocation and Deallocation muss in DLL und von Locsim aufgerufen werden, sonst tritt ein memory-leak auf.
	**/
	__declspec(dllexport) int stw_deallocate(void*){
		std::cout << "stw_deallocate(void*)" << std::endl;
		return 0;
	}

	/**
	* Speicher Allocation and Deallocation muss in DLL und von Locsim aufgerufen werden, sonst tritt ein memory-leak auf.
	**/
	__declspec(dllexport) int stw_deallocate_array(void*){
		std::cout << "stw_deallocate_array(void*)" << std::endl;
		return 0;
	}

};

int main()
{
	std::cout << "DLL compliation successfull!" << std::endl;
	system("PAUSE");

	return 0;
}