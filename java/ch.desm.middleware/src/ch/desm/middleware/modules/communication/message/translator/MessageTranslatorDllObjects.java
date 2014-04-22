package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectSignal;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrainPosition;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectWeiche;
import ch.desm.middleware.modules.communication.message.type.MessageDll;


/**
 * TODO implementation
 * @author Sebastian
 *
 */
public class MessageTranslatorDllObjects extends MessageTranslatorBase {

	/**
	 *  Case "INI1"
                    ' Init or Reset has happened, Send all Cabine States and end with INI2

                    '...send all values and end it if sending an INI2

                    SentToLocSimAllCabineStates(True)


                    Label22.Text = "LocSimState: Send and Receive"
                Case "INI2"
                    MsgBox("Error: INI2 received, but from Locsim Simulator....")
                Case "INI7"
                    ' Simulator not overloaded anymmore; send all Cabine States

                    '...send all values
                    Label22.Text = "LocSimState: Send and Receive"
                Case "INI8"
                    ' Simulator is overloaded, stop sending only recieve

                    Label22.Text = "LocSimState: Overloaded ... do not send to LocSim, only receive"

                Case Else


                    If Mid(commandstring, 1, 1) = "X" Then
                        Typ = Mid(commandstring, 2, 1)
                        Kanal = Mid(commandstring, 3, 2)
                        Daten = Mid(commandstring, 5, 4)



                        Select Case Typ


                            Case "U" ' Digital
                                Dim Daten_X As String = Mid(Daten, 3, 1)
                                Dim Daten_Y As String = Mid(Daten, 4, 1)

                                Select Case Kanal
                                    ' NOTE: ADD + 1 TO THE KANAL IF YOU ARE READNIG THE KOPPLUNGS FILE!!!!
                                    Case "00"
                                        ' Schleuder Bremse Lampe
                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Timer00.Tag = "1" & Daten_X & Daten_Y
                                                Timer00.Enabled = True
                                                UpdateBoBoGUI(&H23, &HF0, 2)
                                                SendToCabine(&H23, &HF0, 2, 0, 1)

                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Timer00.Tag = "1" & Daten_X & Daten_Y
                                                Timer00.Enabled = True
                                                UpdateBoBoGUI(&H23, &HF0, 1)
                                                SendToCabine(&H23, &HF0, 1, 0, 1)
                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Timer00.Enabled = False
                                            UpdateBoBoGUI(&H23, &HF0, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H23, &HF0, CInt("&H" & Daten) + 1, 0, 1)
                                        End If

                                    Case "01"
                                        ' Heizlampe
                                        UpdateBoBoGUI(&H42, &H36, CInt(Daten) + 1)
                                        SendToCabine(&H42, &H36, CInt(Daten) + 1, 0, 1)
                                        'UBW32 LED
                                        SendToCabine(&H42, &H36, CInt(Daten) + 1, 1, 1)
                                    Case "02"
                                        ' Venti Lampe
                                        UpdateBoBoGUI(&H44, &H5C, CInt(Daten) + 1)
                                        SendToCabine(&H44, &H5C, CInt(Daten) + 1, 0, 1)
                                    Case "03"
                                        ' stufenschalter lampe
                                        UpdateBoBoGUI(&H3F, &HAC, CInt(Daten) + 1)
                                        SendToCabine(&H3F, &HAC, CInt(Daten) + 1, 0, 1)
                                        SendToCabine(&H3F, &HAC, CInt(Daten) + 1, 1, 1)
                                    Case "04"
                                        ' Abfertigung
                                        UpdateBoBoGUI(&H46, &HB4, CInt(Daten) + 1)
                                        SendToCabine(&H46, &HB4, CInt(Daten) + 1, 0, 1)
                                        SendToCabine(&H46, &HB4, CInt(Daten) + 1, 1, 1)


                                    Case "05" ' /E< "AnzeigeTürenLinks"

                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Timer05.Tag = "1" & Daten_X & Daten_Y
                                                Timer05.Enabled = True
                                                UpdateBoBoGUI(&H47, &H91, 2)
                                                SendToCabine(&H47, &H91, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Timer05.Tag = "1" & Daten_X & Daten_Y
                                                Timer05.Enabled = True
                                                UpdateBoBoGUI(&H47, &H91, 1)
                                                SendToCabine(&H47, &H91, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Timer05.Enabled = False
                                            UpdateBoBoGUI(&H47, &H91, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H47, &H91, CInt("&H" & Daten) + 1, 0, 1)
                                        End If

                                    Case "06"  ' V "AnzeigeTürverriegelung"


                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Timer06.Tag = "1" & Daten_X & Daten_Y
                                                Timer06.Enabled = True
                                                UpdateBoBoGUI(&H48, &H44, 2)
                                                SendToCabine(&H48, &H44, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Timer06.Tag = "1" & Daten_X & Daten_Y
                                                Timer06.Enabled = True
                                                UpdateBoBoGUI(&H48, &H44, 1)
                                                SendToCabine(&H48, &H44, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Timer06.Enabled = False
                                            UpdateBoBoGUI(&H48, &H44, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H48, &H44, CInt("&H" & Daten) + 1, 0, 1)
                                        End If


                                    Case "07" ' /E> Case "AnzeigeTürenRechts"

                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Timer07.Tag = "1" & Daten_X & Daten_Y
                                                Timer07.Enabled = True
                                                UpdateBoBoGUI(&H47, &H87, 2)
                                                SendToCabine(&H47, &H87, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Timer07.Tag = "1" & Daten_X & Daten_Y
                                                Timer07.Enabled = True
                                                UpdateBoBoGUI(&H47, &H87, 1)
                                                SendToCabine(&H47, &H87, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Timer07.Enabled = False
                                            UpdateBoBoGUI(&H47, &H87, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H47, &H87, CInt("&H" & Daten) + 1, 0, 1)
                                        End If


                                        ' zusi...


                                    Case "08"
                                        ' Lampe M-Taste
                                        ' Ist mit dem Magnet verbunden (HW)
                                        ' --> do nothing 
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Received from LocSim Lampe M-Tast; ignore ...  Daten: " & Daten & vbCrLf)
                                        End If
                                    Case "09"
                                        ' Halte relais M-Taste
                                        ' Sollte über 40 km/h auslösen, bekommen aber nichts vom LOCSIM, habe darum in der Geschwidnigkeit dies abgefangen und sende den be
                                        'If Daten <> 0 Then
                                        '  UpdateBoBoGUI(&H47, &HE1, CInt(Daten) + 1)
                                        '   SendToCabine(&H47, &HE1, CInt(Daten) + 1, 0, 1)
                                        'Else

                                        '    End If
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Received from LocSim Magnet M-Tast; ignore ...  Daten: " & Daten & vbCrLf)
                                        End If
                                    Case "10"
                                        ' Notbremsventil - Annahme = "EPVentil243"
                                        If CheckBox2.Checked Then
                                            UpdateBoBoGUI(&H5E, &HEC, CInt(Daten) + 1)
                                            SendToCabine(&H5E, &HEC, CInt(Daten) + 1, 0, 1)
                                        End If

                                    Case "11"
                                        ' Quitung Gelb
                                        If CheckBox3.Checked Then
                                            If IgnoreZusiForNow Then
                                                MyFile.Write("Received from LocSim Quitung Gelb - Daten: " & Daten & " but ignoring..." & vbCrLf)
                                            Else
                                                MyFile.Write("Received from LocSim Quitung Gelb - Daten: " & Daten & vbCrLf)
                                            End If

                                        End If


                                        If CInt(Daten) = 1 Then
                                            If IgnoreZusiForNow Then
                                                ' do nothing, ignore locsim blink...
                                                If CheckBox3.Checked Then
                                                    MyFile.Write("IgnoreZusiForNow is true, therefore do nothing, ignore locsim blink..." & Daten & vbCrLf)
                                                End If
                                            Else

                                                If Form2.Button18.Tag = 1 Then

                                                    ' M-Taste Nicht Gedrückt ...
                                                    If CheckBox3.Checked Then
                                                        MyFile.Write("Got Quittung Gelb from Loksim and M-Taste is NOT gedrückt" & Daten & vbCrLf)
                                                    End If
                                                    ' Make sure we ignore further "quit gelb" for 8 seconds
                                                    IgnoreZusiForNow = True
                                                    IgnoreZusi.Enabled = True

                                                    ' check if Vorgesorgt:

                                                    If Form2.Button25.Tag = 2 Then
                                                        ' Rückstelltaste bereits gedrückt
                                                        SendToCabine(&H5D, &HC0, 5, 0, 1)
                                                        If CheckBox3.Checked Then
                                                            MyFile.Write("Rückstelltaste bereits gedrückt" & Daten & vbCrLf)
                                                        End If
                                                    Else
                                                        ' Rückstelltaste nicht gedrückt
                                                        SendToCabine(&H5D, &HC0, 6, 0, 1)
                                                        ZusiWarteAufQuittung = True
                                                        If CheckBox3.Checked Then
                                                            MyFile.Write("Rückstelltaste nicht gedrückt" & Daten & vbCrLf)
                                                        End If
                                                    End If
                                                Else
                                                    ' M-Taste ist gedrückt ...
                                                    SendToCabine(&H5D, &HC0, 3, 0, 1)
                                                    If CheckBox3.Checked Then
                                                        MyFile.Write("Got Quittung Gelb from Loksim and have M-Taste gedrückt" & Daten & vbCrLf)
                                                    End If
                                                    ' Make sure we ignore further "quit gelb" for 8 seconds
                                                    IgnoreZusiForNow = True
                                                    IgnoreZusi.Enabled = True
                                                End If
                                            End If

                                        Else
                                            If CheckBox3.Checked Then
                                                MyFile.Write("CInt(Daten) wurde nicht als 1 interpretiert. Aktueller Daten String ist: " & Daten & vbCrLf)
                                            End If
                                        End If


                                    Case "12"
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Received from LocSim Quitung Rot - Daten: " & Daten & vbCrLf)
                                        End If
                                        ' Quitung Rot
                                        ' Hauptsignal überfahren
                                        If Daten <> 0 Then
                                            If Form2.Button18.Tag = 1 Then
                                                SendToCabine(&H5D, &HC0, 1, 0, 1)
                                                If CheckBox3.Checked Then
                                                    MyFile.Write("Got Quittung Rot from Loksim and have M-Taste NOT gedrückt" & Daten & vbCrLf)
                                                End If
                                            Else
                                                ' M-Taste ist gedrückt ...
                                                SendToCabine(&H5D, &HC0, 3, 0, 1)
                                                If CheckBox3.Checked Then
                                                    MyFile.Write("Got Quittung Rot from Loksim and have M-Taste gedrückt" & Daten & vbCrLf)
                                                End If
                                            End If

                                        Else
                                            ' Signale Reset!! Zusi zuürcksetzen
                                            SendToCabine(&H5D, &HC0, 0, 0, 1)
                                        End If


                                    Case Else
                                        ' MsgBox("Unknown Kanal <" & Kanal & "> in in executeLocSimCommand typ U received")
                                End Select


                            Case "V" ' Analog

                                Select Case Kanal

                                    Case "00"
                                        'Tacho "AnzeigGeschwindigkeit"
                                        Dim v As Integer

                                        v = Int(159 * CInt("&H" & Daten) / 65535)
                                        Label35.Text = v & " / " & Daten
                                        If v > 159 Then
                                            ' limitation LZB
                                            v = 159
                                        End If

                                        UpdateBoBoGUI(&H24, &HE0, v)
                                        SendToCabine(&H24, &HE0, v, 0, 1)


                                        ' If v > 40 Then
                                        'SendToCabine(&H47, &HE1, 1, 0, 1)
                                        ' Else
                                        'SendToCabine(&H47, &HE1, 2, 0, 1)
                                        ' End If

                                    Case "01"
                                        'Fahrleitungs Voltmeter "AnzeigeFahrleitungsSpannung"
                                        Dim LocalFahrLeitungsSpannungHex = Daten
                                        Dim LocalFahrLeitungsSpannung As Integer


                                        LocalFahrLeitungsSpannung = CInt("&H" & Daten)

                                        If LocalFahrLeitungsSpannung < 47331 Then
                                            LocalFahrLeitungsSpannung = 0
                                        ElseIf LocalFahrLeitungsSpannung > 65535 Then
                                            LocalFahrLeitungsSpannung = 65535
                                        End If



                                        LocalFahrLeitungsSpannung = (255 * LocalFahrLeitungsSpannung / 65535) - 25
                                        If LocalFahrLeitungsSpannung > 230 Then
                                            LocalFahrLeitungsSpannung = 230
                                        ElseIf LocalFahrLeitungsSpannung < 0 Then
                                            LocalFahrLeitungsSpannung = 0
                                        End If
                                        UpdateBoBoGUI(&H1C, &HE8, LocalFahrLeitungsSpannung)
                                        SendToCabine(&H1C, &HE8, LocalFahrLeitungsSpannung, 0, 1)
                                    Case "02"
                                        'Ampere Meter / "AnzeigeFahrstrom"
                                        UpdateBoBoGUI(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535))
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 0, 1)
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 1, 1)
                                    Case "03"
                                        'Difference Strom / AnzeigeDifferenzStrom
                                        UpdateBoBoGUI(&H1E, &HE6, Int(255 * CInt("&H" & Daten) / 65535))
                                        SendToCabine(&H1E, &HE6, Int(255 * CInt("&H" & Daten) / 65535), 0, 1)
                                    Case "04"
                                        TextBox12.AppendText("Batterie Strom: " & Daten & vbCrLf)

                                        If CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Batterie Strom: " & Daten & vbCrLf)
                                        End If
                                    Case "05"
                                        TextBox12.AppendText("Batterie Spannung: " & Daten & vbCrLf)

                                        If CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Batterie Spannung: " & Daten & vbCrLf)
                                        End If
                                    Case "06"
                                        TextBox12.AppendText("Heizstrom:" & Daten & vbCrLf)
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Heizstrom:" & Daten & vbCrLf)
                                        End If
                                    Case "07"
                                        TextBox12.AppendText("Zylinder: " & Daten & vbCrLf)

                                        ' Ausgabe BZ Druck from Locsim in UI
                                        ' Note: simulfile has following paramter:

                                        ' instr232max(480)=10.  
                                        ' instr232(480) = 8 / Zylinder

                                        ' instr232max(90) = 9.9
                                        ' instr232(90) = 10 / Hauptleitung

                                        Dim BZinBar As Double = CInt("&H" & Daten) * 10.0 / 65535
                                        Form5.Label17.Text = "0x" & Daten
                                        Form5.Label18.Text = CInt("&H" & Daten) & " /"
                                        Form5.Label19.Text = BZinBar.ToString("0.00") & " bar"

                                        If CheckBox3.Checked Then
                                            MyDruckFile.Write(DateTime.Now.ToString() & " :: BZ-Druck from LoCsim ::               " & BZinBar.ToString("0.00") & " bar    " & CInt("&H" & Daten) & " / 0x" & Daten & vbCrLf)
                                            MyFile.Write("Ignored .... " & Now & " Zylinder: " & Daten & vbCrLf)
                                        End If
                                    Case "08"
                                        TextBox12.AppendText("Speiseleitung:" & Daten & vbCrLf)
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Speiseleitung:" & Daten & vbCrLf)
                                        End If
                                    Case "09"

                                        TextBox12.AppendText("Hauptleitung:" & Daten & vbCrLf)

                                        ' Ausgabe HL Druck from LocSim in the UI
                                        ' Note: simulfile has following paramter:

                                        ' instr232max(480)=10.  
                                        ' instr232(480) = 8 / Zylinder

                                        ' instr232max(90) = 9.9
                                        ' instr232(90) = 10 / Hauptleitung

                                        Dim HLinBar As Double = CInt("&H" & Daten) * 9.9 / 65535
                                        Form5.Label7.Text = "0x" & Daten
                                        Form5.Label8.Text = CInt("&H" & Daten) & " /"
                                        Form5.Label11.Text = HLinBar.ToString("0.00") & " bar"

                                        If CheckBox3.Checked Then
                                            MyDruckFile.Write(DateTime.Now.ToString() & " :: HL-Druck from LoCsim ::               " & HLinBar.ToString("0.00") & " bar    " & CInt("&H" & Daten) & " / 0x" & Daten & vbCrLf)
                                            MyFile.Write("Ignored .... " & Now & " Hauptleitung:" & Daten & vbCrLf)
                                        End If
                                    Case "10"
                                        TextBox12.AppendText("ibrems23:" & Daten & vbCrLf)

                                        'Ampere Meter / "AnzeigeFahrstrom"
                                        UpdateBoBoGUI(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535))
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 0, 1)
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 1, 1)

                                    Case Else
                                        TextBox12.AppendText("Kanal: " & Kanal & " , Daten: " & Daten & vbCrLf)
                                        If CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Kanal: " & Kanal & " , Daten: " & Daten & vbCrLf)
                                        End If
                                End Select

                            Case "S"
                                ' Symbole: Not in use here...
                            Case "L"
                                ' Lable: Not in use here..
                            Case "D"
                                ' Diskretisierungs Wert... (Hysterese für Analogkanäle)
                            Case Else
                                MsgBox("Unknown Typ <" & Typ & "> in in executeLocSimCommand  received")
                        End Select
	 */
	
	
	
	
	/**
	 * 						
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(EndpointDllObjectTrainPosition obj){
		
		String s = MessageDll.DLL_MESSAGE_TRAINPOSITION;
		s += obj.gleisList.toString();
		s+=";";
		
		return s;
	}
	
	/**
	 * 
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(EndpointDllObjectSignal obj){
		
		String s = MessageDll.DLL_MESSAGE_SIGNAL;
		s += obj.signalId;
		s+=";";
		s += obj.stellung;
		s+=";";
		
		return s;
	}
	
	/**
	 * 
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(EndpointDllObjectWeiche obj){
		
		String s = MessageDll.DLL_MESSAGE_WEICHE;
		s += obj.weicheId;
		s+=";";
		s += obj.gleisId;
		s+=";";
//		s += obj.stellung;
//		s+=";";
		
		return s;
	}
}
