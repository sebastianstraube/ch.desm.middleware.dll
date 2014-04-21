package ch.desm.middleware.modules.component.simulation;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;

public class LocsimEndpointRs232Configuration {

	//TODO refactoring
	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "192,0,0,24560,96,1,2";
	public static final String PINBITMASK_INPUT_ANALOG = "11";
	
	private static Map<String, EnumEndpointUbw32RegisterDigital> mapDigital;
	private static Map<String, EnumEndpointUbw32RegisterAnalog> mapAnalog;
	
	public LocsimEndpointRs232Configuration(){
		mapDigital = new HashMap<String, EnumEndpointUbw32RegisterDigital>();
		mapAnalog = new HashMap<String, EnumEndpointUbw32RegisterAnalog>();
		
		this.initializeDigital();
	}
	
	public Map<String, EnumEndpointUbw32RegisterDigital> getMapInputDigital(){
		return mapDigital;
	}
	
	public Map<String, EnumEndpointUbw32RegisterAnalog> getMapInputAnalog(){
		return mapAnalog;
	}
	
	public boolean isKeyAvailable(String id){
		return mapDigital.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return mapDigital.containsValue(id);
	}
	
	private void initializeDigital(){
		mapDigital.put("1.90.02", EnumEndpointUbw32RegisterDigital.E0); //Störungslampe Einfahrvorsignal F*

	
	}
	
	public void initializeAnalog(){
		mapAnalog.put("8.91.01", EnumEndpointUbw32RegisterAnalog.AN0); //FSS Grundstellung
		mapAnalog.put("8.91.03", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10° F 
		mapAnalog.put("8.91.04", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30° F 
		mapAnalog.put("8.91.05", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45° F 
		mapAnalog.put("8.91.06", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80° F 
		mapAnalog.put("8.91.07", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90° F 
		mapAnalog.put("8.91.19", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10° EG 
		mapAnalog.put("8.91.20", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30° EG 
		mapAnalog.put("8.91.21", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45° EG 
		mapAnalog.put("8.91.22", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80° EG 
		mapAnalog.put("8.91.23", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90° EG 

	}
	
	/**
	 * TODO implementation for multi messaging analog
	 * @param register
	 */
	public String getGlobalIdFSS(EnumEndpointUbw32RegisterAnalog register, int value){
		
		String globalId = "";
		
		//Quelle: Verdrahtungsliste - Analog Input Definition
		switch(register){
			case AN0: 	if(value > 235 && value < 246){globalId = "8.91.07";} else //FSS 90° F
						if(value > 296 && value < 308){globalId = "8.91.06";} else //FSS 80° F
						if(value > 349 && value < 362){globalId = "8.91.05";} else //FSS 45° F
						if(value > 390 && value < 402){globalId = "8.91.04";} else //FSS 30° F
						if(value > 417 && value < 429){globalId = "8.91.03";} else //FSS 10° F
						if(value > 461 && value < 474){globalId = "8.91.01";} else //FSS Grundstellung
						if(value > 496 && value < 513){globalId = "8.91.19";} else //FSS 10° EG
						if(value > 522 && value < 534){globalId = "8.91.20";} else //FSS 30° EG
						if(value > 570 && value < 582){globalId = "8.91.21";} else //FSS 45° EG
						if(value > 637 && value < 654){globalId = "8.91.22";} else //FSS 80° EG
						if(value > 667 && value < 682){globalId = "8.91.23";} else //FSS 90° EG 
						{
							System.out.println("the register " + register.name() + " has no mapped value: " + value);
						}
			default: System.err.println("no mapping defined for analog register:" + register.name());
		}
		
		return globalId;
	}

	
	/**
	 * 
	 * 	PC > SPS	[X]	[D,L,S,U,V]	[00-99]	[0000-FFFF] [Y]
	 *	PC < SPS	[X]	[U,V]		[00-99]	[0000-FFFF] [Y]
	 * 
	 *  Case "BVHahn"
                SendBufferString = "XV40000" & Chr(47 + data) & "Y"

            Case "KompressorSchalter"
                If data = 1 Then  'A
                    SendBufferString = "X" & "U" & "16" & "0001Y" & "X" & "V" & "17" & "0000Y"
                ElseIf data = 2 Then '0
                    SendBufferString = "X" & "U" & "16" & "0000Y" & "X" & "V" & "17" & "0000Y"
                Else ' data = 3 ' D
                    SendBufferString = "X" & "U" & "17" & "0001Y" & "X" & "V" & "16" & "0000Y"
                End If

            Case "Auslöseschalter"
                'Directly Connected in the Cabine



' ####################################################################################################################
                ' 
                '     Bremssystem:
                '
                '       Kabine hat ein eigenes Druckluft System, 
                '       welches den Hauptleitungs Druck und den Bremszylinder Druck simuliert.
                '       Der jeweilige Druck wird von einem Sensor eingelesen.
                '       Ein Anpassung in der Simulation ist deshalb nötig, weil nicht Hebelstellungen sonder direkt der Druck
                '       übermittelt wird.
                '
                '    SimulFile in LocSim:
                '
                '   sch232(1) = 1 / Hauptleitungsdruck
                '   sch2320(1)= .38    /       .38  /    
                '   sch232min(1)=.0  /
                '   sch232max(1)=.38       /.53  /5.3bar = 0.53 p.u.
                '
                '   ; wird nicht benötigt:
                '   ;sch232(2)=2          /Rangierbremse
                '   ;sch2320(2)= .0 
                '   ;sch232min(2)=.0   
                '   ;sch232max(2)=.27    /4.9bar = 0.27 p.u.
                '

                '   sch232(41)=2          /Bremszylinder Druck
                '   sch2320(41) = 0.0
                '   sch232min(41) = 0.0
                '   sch232max(41)=.27    /4.9bar = 0.27 p.u.

                ' ###################################################################################################


            Case "HLDruck" ' Vom Drucksensor
                '0x00 /   0  -->  0 bar
                '0x66 / 102  -->  4 bar
                '0x80 / 128  -->  5 bar
                '0xff / 255  --> 10 bar

                If CheckBox5.Checked Then
                    ' ignore HL druck
                Else

                    ' Jitter / Max value / Convert
                    Dim dataConvert As Integer
                    Dim HLLocsimString As String


                    Dim HLinBar As Double = data / 25.5
                    dataConvert = (65535 / 5.3) * HLinBar



                    'Prepapring string value for LocSim (also stretch from 0xFF to 0xFFFF)
                    HLLocsimString = Hex(dataConvert)

                    If HLLocsimString.Length = 1 Then
                        HLLocsimString = "000" & HLLocsimString
                    ElseIf HLLocsimString.Length = 2 Then
                        HLLocsimString = "00" & HLLocsimString
                    ElseIf HLLocsimString.Length = 3 Then
                        HLLocsimString = "0" & HLLocsimString
                    ElseIf HLLocsimString.Length = 4 Then
                        HLLocsimString = HLLocsimString
                    Else
                        MsgBox("Error in HLLocsimString! Value is: " & HLLocsimString & " and its length is: " & HLLocsimString.Length)
                    End If


                    Form2.Label33.Text = "HL Druck Data Value from Cabine (00..FF): 0x" & data & " / 0x" & HLLocsimString


                    ' Ausbgabe Druck UI
                    Form5.Label3.Text = data & " /"
                    Form5.Label9.Text = "0x" & Hex(data)
                    Form5.Label4.Text = HLinBar.ToString("0.00") & " bar"
                    Form5.Label6.Text = dataConvert & " /"
                    Form5.Label10.Text = "0x" & HLLocsimString
                    If CheckBox3.Checked Then
                        MyDruckFile.Write(DateTime.Now.ToString() & " :: HL-Druck from Cabine :: " & data & " /0x" & Hex(data) & "    " & HLinBar.ToString("0.00") & " bar    " & dataConvert & " /0x" & HLLocsimString & vbCrLf)
                    End If


                    Label33.Text = "HL Druck Data Value from Cabine (00..FF): 0x" & data
                    Label36.Text = "HL in bar (Dispatcher interpretation): " & HLinBar
                    Label37.Text = "Sent to LocSim 0x" & HLLocsimString
                    Label38.Text = "HL Druck Data Value from Cabine (00..FF): 0x" & data & " / HL in bar: " & HLinBar & " / 0x" & HLLocsimString
                    SendBufferString = "X" & "V" & "00" & HLLocsimString & "Y"

                    End If



            Case "BremszylinderDruck" ' Vom Drucksensor
                    '0x00 /   0  -->  0 bar
                    '0x66 / 102  -->  4 bar
                    '0x80 / 128  -->  5 bar
                    '0xff / 255  --> 10 bar

                If CheckBox6.Checked Then
                    ' ignore BZ druck from the Cabine
                Else
                    ' stretch data value from FF to FFFF
                    Dim dataConvert2 As Integer
                    Dim BZLocsimString As String

                    Dim BZinBar As Double = data / 25.5
                    dataConvert2 = (Form4.TrackBar1.Value / 4.0) * BZinBar

                    'Prepapring string value for LocSim
                    BZLocsimString = Hex(dataConvert2)

                    If BZLocsimString.Length = 1 Then
                        BZLocsimString = "000" & BZLocsimString
                    ElseIf BZLocsimString.Length = 2 Then
                        BZLocsimString = "00" & BZLocsimString
                    ElseIf BZLocsimString.Length = 3 Then
                        BZLocsimString = "0" & BZLocsimString
                    ElseIf BZLocsimString.Length = 4 Then
                    Else
                        MsgBox("Error in BZLocsimString! Value is: " & BZLocsimString & " and its length is: " & BZLocsimString.Length)

                    End If

                    ' Ausbgabe Druck UI
                    Form5.Label25.Text = data & " /"
                    Form5.Label21.Text = "0x" & Hex(data)
                    Form5.Label24.Text = BZinBar.ToString("0.00") & " bar"
                    Form5.Label22.Text = dataConvert2 & " /"
                    Form5.Label20.Text = "0x" & BZLocsimString
                    If CheckBox3.Checked Then
                        MyDruckFile.Write(DateTime.Now.ToString() & " :: BZ-Druck from Cabine :: " & data & " /0x" & Hex(data) & "    " & BZinBar.ToString("0.00") & " bar    " & dataConvert2 & " /0x" & BZLocsimString & vbCrLf)
                    End If


                    'Display values on UI
                    Form2.Label34.Text = "BZ Druck Value Sent to Simulation: 0x" & data & " / 0x" & BZLocsimString
                    Label34.Text = "BZ Druck Data Value from Cabine (00..FF): 0x" & data & " / BZ Druck Value Sent to Simulation (0000..FFFF): 0x" & BZLocsimString

                    'Send to LocSim
                    SendBufferString = "X" & "V" & "01" & BZLocsimString & "Y"

                End If


            Case "FahrleitungsSpannung" ' Umgebung
                    ' Implemented in LocSim
            Case "Fahrschalter"
                    If data = 1 Then
                        'Bremses +
                    SendBufferString = "X" & "U" & "08" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 2 Then
                        'Bremses °
                    SendBufferString = "X" & "U" & "07" & "0001" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 3 Then
                        'Bremsen -
                    SendBufferString = "X" & "U" & "06" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 4 Then
                        '0
                    SendBufferString = "X" & "U" & "05" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 5 Then
                        'Fahren -
                    SendBufferString = "X" & "U" & "04" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 6 Then
                        'Fahren °
                    SendBufferString = "X" & "U" & "03" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 7 Then
                        'Fahren M
                    SendBufferString = "X" & "U" & "02" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 8 Then
                        'Fahren +
                    SendBufferString = "X" & "U" & "01" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                    ElseIf data = 9 Then
                        'Fahren ++
                    SendBufferString = "X" & "U" & "00" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y"
                    End If


            Case "FahrtrichtungSchalter"
                    If CheckBox7.Checked Then
                        ' Fix for HW Problem
                        If RadioButton18.Checked Then
                            'Vorwärts
                        SendBufferString = "X" & "U" & "09" & "0001" & "Y" & "X" & "U" & "10" & "0000" & "Y"
                        ElseIf RadioButton17.Checked Then
                            'Neutral
                            ' Does not Exisit in LocSim 

                            SendBufferString = "X" & "U" & "10" & "0000" & "Y" & "X" & "U" & "09" & "0000" & "Y"
                        ElseIf RadioButton16.Checked Then
                            'Rückwärts
                        SendBufferString = "X" & "U" & "10" & "0001" & "Y" & "X" & "U" & "09" & "0000" & "Y"
                        End If
                    Else
                        If data = 1 Then
                            'Rückwärts
                        SendBufferString = "X" & "U" & "10" & "0001" & "Y" & "X" & "U" & "09" & "0000" & "Y"
                        ElseIf data = 2 Then
                            'Neutral
                            ' Does not Exisit in LocSim 

                            SendBufferString = "X" & "U" & "10" & "0000" & "Y" & "X" & "U" & "09" & "0000" & "Y"

                        ElseIf data = 3 Then
                            'Vorwärts
                        SendBufferString = "X" & "U" & "09" & "0001" & "Y" & "X" & "U" & "10" & "0000" & "Y"
                        End If
                    End If


            Case "HauptschalterSchalter" ' Completed

                    ' Send Hauptschalter stellung together with Kompressor (da die Kabine das nicht schickt...)
                    If data = 1 Then
                        ' Hauptschalter aus, Kompressor 0
                        SendBufferString = "X" & "U" & "18" & "000" & Chr(47 + data) & "Y" & "X" & "U" & "16" & "0000Y" & "X" & "V" & "17" & "0000Y"
                    Else
                        ' Hauptschalter ein, Kompressor A
                        SendBufferString = "X" & "U" & "18" & "000" & Chr(47 + data) & "Y" & "X" & "U" & "16" & "0001Y" & "X" & "V" & "17" & "0000Y"
                    End If




            Case "SchalterLampenAussen"
                    Form2.AussenLicht = data



                    'Lampe 1R
                    If (data And 128) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = "X" & "U" & "34" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe 1W
                    If (data And 16) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "35" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe 2R
                    If (data And 64) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "36" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe 2W
                    If (data And 8) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "37" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe 3R
                    If (data And 32) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "38" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe 3W
                    If (data And 4) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "39" & "000" & Chr(48 + bitSet) & "Y"

                    UpdateBoBoGUI(&H7C, &H38, data)

                    ' Muss hier kurzgeschlossen werden mit den "Cabine-Actors"
                    SendToCabine(&H7C, &H38, data, 0, 1)


            Case "SchalterFührerstandsLampe"
                    Form2.InnenLicht = data

                    'Lampe Führerstandsbeleuchtung
                    If (data And 128) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = "X" & "U" & "44" & "000" & Chr(48 + bitSet) & "Y"
                    'Lampe Fahrplanbeleuchtung (nehm ich halt das, anstatt den Maschinene Raum :-) )
                    If (data And 64) Then
                        bitSet = 1
                    Else
                        bitSet = 0
                    End If
                    SendBufferString = SendBufferString & "X" & "U" & "43" & "000" & Chr(48 + bitSet) & "Y"

                    UpdateBoBoGUI(&H7E, &H90, data)

                    ' Muss hier kurzgeschlossen werden mit den "Cabine-Actors"
                    SendToCabine(&H7E, &H90, data, 0, 1)


            Case "M-Taste"
                    SendBufferString = "X" & "U" & "28" & "000" & Chr(47 + data) & "Y"

            Case "SchalterTürfreigabe-Links"
                    SendBufferString = "X" & "U" & "23" & "000" & Chr(47 + data) & "Y"

            Case "SchalterTürfreigabe-Rechts"
                    SendBufferString = "X" & "U" & "25" & "000" & Chr(47 + data) & "Y"

            Case "SchalterTürveriegelung"
                    SendBufferString = "X" & "U" & "24" & "000" & Chr(47 + data) & "Y"

            Case "WagenTürenZustandInfo" ' Umgebung
                    'Implemented in LocSim


            Case "SchalterZugsbeleuchtung"
                    ' Later ...

            Case "SchalterZugsammelschiene"
                    SendBufferString = "X" & "U" & "15" & "000" & Chr(47 + data) & "Y"

            Case "DruckknopfSignalPfeife"

                    If data = 1 Then
                        'Aus
                        '.... does not exists
                        SendBufferString = "X" & "U" & "30" & "0000" & "Y" & "X" & "U" & "31" & "0000" & "Y"
                        'SendBufferString = "X" & "U" & "31" & "0000" & "Y"
                    ElseIf data = 2 Then
                        'Stellung 1
                        SendBufferString = "X" & "U" & "30" & "0001" & "Y" & "X" & "U" & "31" & "0000" & "Y"
                    ElseIf data = 3 Then
                        'Stellung 2
                        SendBufferString = "X" & "U" & "31" & "0001" & "Y" & "X" & "U" & "30" & "0000" & "Y"
                    End If


            Case "RückstelletasteZugsicherung"

                    UpdateBoBoGUI(&H5E, &H88, data)
                    SendBufferString = "X" & "U" & "32" & "000" & Chr(47 + data) & "Y"

                    If ZusiWarteAufQuittung And (data = 2) Then
                        ' Quittung auf ZusiWarnung Vorsignal erhalten...
                        ZusiWarteAufQuittung = False
                        SendToCabine(&H5D, &HC0, 4, 0, 1)
                        If CheckBox3.Checked Then
                            MyFile.Write("Quittung auf ZusiWarnung Vorsignal erhalten..." & data & vbCrLf)
                        End If
                    End If


            Case "SchleuderbremseTaste"
                    SendBufferString = "X" & "U" & "22" & "000" & Chr(47 + data) & "Y"


            Case "SchlüsselSchalterAbfertigungsBefehl"
                    'SendBufferString = "X" & "U" & "00" & "000" & Chr(47 + data) & "Y"
                    ' Not implemented...
            Case "SchalterSteuerstrom" ' Completed!
                    SendBufferString = "XU20000" & Chr(47 + data) & "Y"

            Case "SchalterStromabnehmer" ' Completed
                    SendBufferString = "X" & "U" & "19" & "000" & Chr(47 + data) & "Y"

            Case "Totmannpedal"

                    If CheckBox4.Checked Then
                        ' was auch immer vom Führerstand kommt, send totmann gedrückt
                        SendBufferString = "X" & "U" & "27" & "0001Y"
                    Else

                    If data = 2 Then
                        ' Gedrückt
                        SendBufferString = "X" & "U" & "27" & "0001Y"

                    Else
                        ' Nicht Gedrückt
                        SendBufferString = "X" & "U" & "27" & "0000Y"
                    End If

                    End If

            Case Else
                    UnknownCommand = True
	 */
}
