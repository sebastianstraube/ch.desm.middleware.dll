Public Class GUIController

    Public SerialController As New UBW32
    Public fso, MyFile
    Public FileString As String = "DispatcherLogfile" & Now & " .txt"
    Public ZusiWarteAufQuittung As Boolean = False
    Public IgnoreZusiForNow As Boolean = False
    Public CabineEventBuffer(-1) As Integer
    Public SimulatorEventBuffer(-1) As Integer
    Public SimulatorStringBuffer As String
    Public UBW32_1_Queue(-1) As CabineCommand

    Public Structure CabineCommand
        Dim command As Integer
        Dim value As Integer
    End Structure
    Public Sub UpdateBoBoGUI(ByVal byte0 As Byte, ByVal byte1 As Byte, ByVal byte2 As Byte)
        Select Case (byte0 * 256 + byte1)

            Case &H5E88
                ' Rückstelltaste Zusi
                If byte2 = 1 Then
                    Form_TrainControl.Button25.BackColor = Color.Black
                    Form_TrainControl.Button25.Tag = 1
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Button25.BackColor = Color.Gold
                    Form_TrainControl.Button25.Tag = 2
                End If

            Case &HF501
                '"SchlüsselSchalterAbfertigungsBefehl"
                ' ...weiss nicht mehr was der Scheiss soll..
            Case &HC350
                '"Hauptschalter"
                If byte2 = 1 Then
                    'Sound Haupschalter aus
                Else
                    'Sound Haupschalter ein
                End If

            Case &H7C38
                '"StirnLampenAussen"
                ' Hier kommt eine Bit-Schiss-Parsing um die Frontlampen anzuzeigen

                Form_TrainControl.Panel1.BackColor = Color.Black
                Form_TrainControl.Panel2.BackColor = Color.Black
                Form_TrainControl.Panel3.BackColor = Color.Black

                If byte2 And 128 Then
                    'LR
                    Form_TrainControl.Panel2.BackColor = Color.Red
                End If

                If byte2 And 64 Then
                    'MR
                    Form_TrainControl.Panel1.BackColor = Color.Red
                End If

                If byte2 And 32 Then
                    'RR
                    Form_TrainControl.Panel3.BackColor = Color.Red
                End If

                If byte2 And 16 Then
                    'LW
                    Form_TrainControl.Panel2.BackColor = Color.White
                End If

                If byte2 And 8 Then
                    'MW
                    Form_TrainControl.Panel1.BackColor = Color.White
                End If

                If byte2 And 4 Then
                    'RW
                    Form_TrainControl.Panel3.BackColor = Color.White
                End If

            Case &H7EF4
                '"FührerstandsLampe"
                ' Hier kommt eine Bit-Schiss-Parsing um die Frontlampen anzuzeigen
                Form_TrainControl.Panel4.BackColor = Color.Black
                Form_TrainControl.Panel17.BackColor = Color.Black


                If byte2 And 128 Then
                    'LR
                    Form_TrainControl.Panel4.BackColor = Color.White
                End If

                If byte2 And 64 Then
                    'MR
                    Form_TrainControl.Panel17.BackColor = Color.White
                End If

            Case &H47E1
                '"Beleuchtung M-Taste"
                ' TBD...
            Case &H47E0
                '"M-Taste"
                If byte2 = 1 Then
                    Form_TrainControl.Button18.BackColor = Color.Black
                    Form_TrainControl.Button18.Tag = 1
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Button18.BackColor = Color.Gold
                    Form_TrainControl.Button18.Tag = 2
                End If

            Case &H1EDC
                '"AnzeigeFahrstrom"
                '4kA = 255
                Form_TrainControl.TrackBar2.Value = byte2
                Form_TrainControl.Label18.Text = Math.Round(4000 * byte2 / 255, 2) & " A"
            Case &H1EE6
                '"AnzeigeDifferenzStrom"
                Form_TrainControl.TrackBar3.Value = byte2
                Form_TrainControl.Label19.Text = Math.Round(1000 * byte2 / 255, 2) & " A"
            Case &H1CE8
                '"AnzeigeFahrleitungsSpannung"
                Form_TrainControl.TrackBar1.Value = byte2
                Form_TrainControl.Label17.Text = Math.Round(20 * byte2 / 255, 2) & " kV"
            Case &H24E0
                '"AnzeigGeschwindigkeit"
                Form_TrainControl.ProgressBar1.Value = byte2
                Form_TrainControl.Label4.Text = byte2 & " km/h"
            Case &H490C
                '"VentilSignalPfeife"
                If byte2 = 1 Then
                    ' Stop PfeifSound
                ElseIf byte2 = 2 Then
                    ' Pfeifsound low On
                ElseIf byte2 = 3 Then
                    'Pfeifsound high On
                End If
            Case &H23F0
                '"AnzeigeSchleuderBremse"
                If byte2 = 1 Then
                    Form_TrainControl.Panel15.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel15.BackColor = Color.Gold
                End If
            Case &H3FAC
                '"StufenSchalterMeldeLampe"
                If byte2 = 1 Then
                    Form_TrainControl.Panel10.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel10.BackColor = Color.Gold
                End If
            Case &H5D5C
                '"SummerLangsamUndSchnellgang"
                If byte2 = 1 Then
                    Form_TrainControl.Panel4.BackColor = Color.Black
                    Form_TrainControl.Label25.Text = "Summer Aus"
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel4.BackColor = Color.Gold
                    Form_TrainControl.Label25.Text = "Summer Hochton"
                ElseIf byte2 = 3 Then
                    Form_TrainControl.Panel4.BackColor = Color.Red
                    Form_TrainControl.Label25.Text = "Summer Tiefton"
                End If

            Case &H46B4
                '"AbfertigungsBefehl"
                If byte2 = 1 Then
                    Form_TrainControl.Panel12.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel12.BackColor = Color.Green
                End If
            Case &H4791
                '"AnzeigeTürenLinks"
                If byte2 = 1 Then
                    Form_TrainControl.Panel8.BackColor = Color.DarkKhaki
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel8.BackColor = Color.Gold
                End If
            Case &H4787
                '"AnzeigeTürenRechts"
                If byte2 = 1 Then
                    Form_TrainControl.Panel5.BackColor = Color.DarkKhaki
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel5.BackColor = Color.Gold
                End If
            Case &H4844
                '"AnzeigeTürverriegelung"
                If byte2 = 1 Then
                    Form_TrainControl.Panel7.BackColor = Color.RosyBrown
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel7.BackColor = Color.Red
                End If
            Case &H7990
                '"AnzeigeZugsbeleuchtung"
                If byte2 = 1 Then
                    Form_TrainControl.Panel9.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel9.BackColor = Color.Gold
                End If
            Case &H4236
                '"AnzeigeZugsammelSchiene"
                If byte2 = 1 Then
                    Form_TrainControl.Panel11.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel11.BackColor = Color.Gold
                End If
            Case &H445C
                '"AnzeigeVentilator"
                If byte2 = 1 Then
                    Form_TrainControl.Panel13.BackColor = Color.Black
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Panel13.BackColor = Color.Gold
                End If


            Case &H5BCC
                '"Tottmanpedal"
                If byte2 = 1 Then
                    Form_TrainControl.Button19.BackColor = Form_TrainControl.OffColor
                    Form_TrainControl.Button19.Tag = 1
                ElseIf byte2 = 2 Then
                    Form_TrainControl.Button19.BackColor = Form_TrainControl.OnColor
                    Form_TrainControl.Button19.Tag = 2
                End If

            Case Else

        End Select

    End Sub
    Public Sub convertBobo2Locsim(ByVal EventString As String, ByVal data As Byte, ByRef SendBufferString As String, ByRef UnknownCommand As Boolean)
        Dim bitSet As Byte

        UnknownCommand = False
        Select Case EventString


            Case "BVHahn"
                SendBufferString = "X" & "V" & "40" & "000" & Chr(47 + data) & "Y"

            Case "KompressorSchalter"
                If data = 1 Then  'A
                    SendBufferString = "X" & "V" & "16" & "0001Y" & "X" & "V" & "17" & "0000Y"
                ElseIf data = 2 Then '0
                    SendBufferString = "X" & "V" & "16" & "0000Y" & "X" & "V" & "17" & "0000Y"
                Else ' data = 3 ' D
                    SendBufferString = "X" & "V" & "17" & "0001Y" & "X" & "V" & "16" & "0000Y"
                End If

            Case "Auslöseschalter"
                'Directly Connected in the Cabine


            Case "HLDruck" ' Vom Drucksensor

                If Form_CommandCenter.CheckBox5.Checked Then
                    ' ignore HL druck
                Else


                    data = data + 6

                    If data > 136 Then
                        data = 136

                    End If

                    Dim dataConvert As Integer
                    dataConvert = data * 255 / 136

                    If dataConvert > 255 Or dataConvert < 0 Then
                        MsgBox(dataConvert)
                    End If
                    Form_TrainControl.Label33.Text = "HL Druck: 0x" & data & " / 0x" & Hex(dataConvert) & "FF"
                    Form_CommandCenter.Label33.Text = "HL Druck: 0x" & data & " / 0x" & Hex(dataConvert) & "FF"



                    If dataConvert < 16 Then
                        SendBufferString = "X" & "V" & "000" & Hex(dataConvert) & "FF" & "Y"
                    Else
                        SendBufferString = "X" & "V" & "00" & Hex(dataConvert) & "FF" & "Y"
                    End If
                End If


            Case "BremszylinderDruck" ' Vom Drucksensor
                If Form_CommandCenter.CheckBox6.Checked Then
                    ' ignore BZ druck
                Else
                    If data > 102 Then
                        ' not more then 4.0 bar
                        data = 102
                    ElseIf data < 3 Then
                        ' jitter on the bottom
                        data = 0
                    Else
                        data = data - 3

                    End If


                    Dim dataConvert2 As Integer
                    Dim BZLocsimString As String
                    dataConvert2 = data * 17664 / 102

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


                    Form_TrainControl.Label34.Text = "BZ Druck: 0x" & data & " / 0x" & BZLocsimString
                    Form_CommandCenter.Label34.Text = "BZ Druck: 0x" & data & " / 0x" & BZLocsimString


                    SendBufferString = "X" & "V" & "01" & BZLocsimString & "Y"

                End If


            Case "FahrleitungsSpannung" ' Umgebung
                ' Implemented in LocSim
            Case "Fahrschalter"
                If data = 1 Then
                    'Bremses +
                    SendBufferString = "X" & "U" & "08" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y"
                ElseIf data = 2 Then
                    'Bremses °
                    SendBufferString = "X" & "U" & "07" & "0001" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "08" & "0000" & "Y"
                ElseIf data = 3 Then
                    'Bremsen -
                    SendBufferString = "X" & "U" & "06" & "0001" & "Y" & "X" & "U" & "07" & "0000" & "Y" & "X" & "U" & "05" & "0000" & "Y"
                ElseIf data = 4 Then
                    '0
                    SendBufferString = "X" & "U" & "05" & "0001" & "Y" & "X" & "U" & "06" & "0000" & "Y" & "X" & "U" & "04" & "0000" & "Y"
                ElseIf data = 5 Then
                    'Fahren -
                    SendBufferString = "X" & "U" & "04" & "0001" & "Y" & "X" & "U" & "05" & "0000" & "Y" & "X" & "U" & "03" & "0000" & "Y"
                ElseIf data = 6 Then
                    'Fahren °
                    SendBufferString = "X" & "U" & "03" & "0001" & "Y" & "X" & "U" & "04" & "0000" & "Y" & "X" & "U" & "02" & "0000" & "Y"
                ElseIf data = 7 Then
                    'Fahren M
                    SendBufferString = "X" & "U" & "02" & "0001" & "Y" & "X" & "U" & "03" & "0000" & "Y" & "X" & "U" & "01" & "0000" & "Y"
                ElseIf data = 8 Then
                    'Fahren +
                    SendBufferString = "X" & "U" & "01" & "0001" & "Y" & "X" & "U" & "02" & "0000" & "Y" & "X" & "U" & "00" & "0000" & "Y"
                ElseIf data = 9 Then
                    'Fahren ++
                    SendBufferString = "X" & "U" & "00" & "0001" & "Y" & "X" & "U" & "01" & "0000" & "Y"
                End If


            Case "FahrtrichtungSchalter"
                If data = 1 Then
                    'Rückwärts
                    SendBufferString = "X" & "U" & "10" & "0001" & "Y"
                ElseIf data = 2 Then
                    'Neutral
                    ' Does not Exisit in LocSim 

                    SendBufferString = "X" & "U" & "10" & "0000" & "Y" & "X" & "U" & "09" & "0000" & "Y"

                ElseIf data = 3 Then
                    'Vorwärts
                    SendBufferString = "X" & "U" & "09" & "0001" & "Y"
                End If

            Case "HauptschalterSchalter" ' Completed
                SendBufferString = "X" & "U" & "18" & "000" & Chr(47 + data) & "Y"



            Case "SchalterLampenAussen"
                Form_TrainControl.AussenLicht = data



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
                Form_TrainControl.InnenLicht = data

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
                    If Form_CommandCenter.CheckBox3.Checked Then
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

                If Form_CommandCenter.CheckBox4.Checked Then
                    ' was auch immer vom Führerstand kommt, send totmann gedrückt
                    SendBufferString = "X" & "U" & "27" & "0001Y"
                Else

                    If (data And 1) Then
                        ' Gedrückt
                        SendBufferString = "X" & "U" & "27" & "0001Y"

                    Else
                        ' Nicht Gedrückt
                        SendBufferString = "X" & "U" & "27" & "0000Y"
                    End If

                End If

            Case Else
                UnknownCommand = True
        End Select

    End Sub
    Public Sub SendToCabine(ByVal byte0 As Byte, ByVal byte1 As Byte, ByVal byte2 As Byte, ByVal COMinstance As Integer, ByVal FührerstandID As Integer)

        ' COMinstance
        '   0 --> MytosBobo (SerialPortCabine)
        '   1 --> UBW32 Board (UBW32_1)
        '   2 --> UBW32 Board (UBW32_2)
        ' FührerstandID
        '   1 --> BoBo
        '   2 --> ...

        Dim COMinstanceString As String
        Dim FührerstandIDString As String

        Select Case COMinstance
            Case 0
                COMinstanceString = "MytosBobo (SerialPortCabine)"
            Case 1
                COMinstanceString = "UBW32 Board (UBW32_1)"
            Case 2
                COMinstanceString = "UBW32 Board (UBW32_2)"
            Case Else
                COMinstanceString = "Undefined COM instance in SendToCabine!!"
        End Select

        Select Case FührerstandID
            Case 1
                FührerstandIDString = "Bobo"
            Case Else
                FührerstandIDString = "Undefined Führstand ID in SendToCabine!!"
        End Select


        Form_CommandCenter.TextBox2.AppendText(byte0 & "," & byte1 & "," & byte2 & " (" & BoboEvent(byte0, byte1) & ") Führerstand: " & FührerstandIDString & " attached to COM instance: " & COMinstanceString & vbCrLf)

        If Form_CommandCenter.CheckBox3.Checked Then
            MyFile.Write("From LocSim " & Now & " " & byte0 & "," & byte1 & "," & byte2 & " (" & BoboEvent(byte0, byte1) & ") Führerstand: " & FührerstandIDString & " attached to COM instance: " & COMinstanceString & vbCrLf)
        End If

        ' insert here routine to send appropirate command to the cabine ....
        'If RE420.Checked Then

        If COMinstance = 0 Then

            Dim sendBuffer As Byte()
            Dim offset As Integer = 0
            Dim count As Integer = 5

            If SerialController.SerialPortCabine.IsOpen Then
                ReDim sendBuffer(5)
                sendBuffer(0) = byte0
                sendBuffer(1) = byte1
                sendBuffer(2) = byte2
                sendBuffer(3) = 0
                sendBuffer(4) = 0
                SerialController.SerialPortCabine.Write(sendBuffer, offset, count)
            Else
                MsgBox("SerialPortCabine is not open!! In SendToCabine")
            End If
        ElseIf COMinstance = 1 Then
            ' send to UBW32_1
            ReDim Preserve UBW32_1_Queue(UBW32_1_Queue.Length)
            UBW32_1_Queue(UBW32_1_Queue.Length - 1).command = byte0 * 256 + byte1
            UBW32_1_Queue(UBW32_1_Queue.Length - 1).value = byte2
            'ElseIf COMinstance = 2 Then
            ' send to UBW32_2
            '   ReDim Preserve UBW32_2_Queue(UBW32_2_Queue.Length)
            '  UBW32_2_Queue(UBW32_2_Queue.Length - 1).command = byte0 * 256 + byte1
            ' UBW32_2_Queue(UBW32_2_Queue.Length - 1).value = byte2
        Else
            MsgBox("Command in SendToCabine not send because: " & COMinstanceString)
        End If
    End Sub
    Public Function BoboEvent(ByVal byte0 As Byte, ByVal byte1 As Byte) As String
        Select Case byte0 * 256 + byte1

            Case &H2777
                BoboEvent = "BVHahn"
            Case &H2776
                BoboEvent = "KompressorSchalter"
            Case &H270F
                BoboEvent = "Auslöseschalter"
            Case &H4E85
                BoboEvent = "BremszylinderDruck" ' Vom Drucksensor
            Case &H2775
                BoboEvent = "HLDruck"            ' Vom Drucksensor
            Case &H1CF2
                BoboEvent = "FahrleitungsSpannung" ' Umgebung
            Case &H3A98
                BoboEvent = "Fahrschalter"
            Case &H36B0
                BoboEvent = "FahrtrichtungSchalter"
            Case &H3458
                BoboEvent = "HauptschalterSchalter"
            Case &H7B70
                BoboEvent = "SchalterLampenAussen"
            Case &H7E90
                BoboEvent = "Totmannpedal"
            Case &H47E0
                BoboEvent = "M-Taste"
            Case &H4790
                BoboEvent = "SchalterTürfreigabe-Links"
            Case &H4786
                BoboEvent = "SchalterTürfreigabe-Rechts"
            Case &H4718
                BoboEvent = "SchalterTürveriegelung"
            Case &H474A
                BoboEvent = "WagenTürenZustandInfo" ' Umgebung
            Case &H797C
                BoboEvent = "SchalterZugsbeleuchtung"
            Case &H4204
                BoboEvent = "SchalterZugsammelschiene"
            Case &H49D4
                BoboEvent = "DruckknopfSignalPfeife"
            Case &H5E88
                BoboEvent = "RückstelletasteZugsicherung"
            Case &H6DC4
                BoboEvent = "SchleuderbremseTaste"
            Case &HF501
                BoboEvent = "SchlüsselSchalterAbfertigungsBefehl"
            Case &H3138
                BoboEvent = "SchalterSteuerstrom"
            Case &HFFF2
                BoboEvent = "Unbekannter BoBo Befehl, wird mit dem Steuerstromschalter geschickt"
            Case &HFFF3
                BoboEvent = "Unbekannter BoBo Befehl, wird mit dem Steuerstromschalter geschickt"
            Case &H3264
                BoboEvent = "SchalterStromabnehmer"
                ' Case &H5BCC
                ' BoboEvent = "Totmannpedal" ' ALT
            Case &H5EEC
                BoboEvent = "EPVentil243"
            Case &HC350
                BoboEvent = "Hauptschalter"
            Case &H7C38
                BoboEvent = "StirnLampenAussen"
            Case &H7EF4
                BoboEvent = "FührerstandsLampe"
            Case &H47E1
                BoboEvent = "BeleuchtungM-Taste"
            Case &H1EDC
                BoboEvent = "AnzeigeFahrstrom"
            Case &H1EE6
                BoboEvent = "AnzeigeDifferenzStrom"
            Case &H1CE8
                BoboEvent = "AnzeigeFahrleitungsSpannung"
            Case &H24E0
                BoboEvent = "AnzeigGeschwindigkeit"
            Case &H490C
                BoboEvent = "VentilSignalPfeife"
            Case &H23F0
                BoboEvent = "AnzeigeSchleuderBremse"
            Case &H3FAC
                BoboEvent = "StufenSchalterMeldeLampe"
            Case &H5D5C
                BoboEvent = "SummerLangsamUndSchnellgang"
            Case &H46B4
                BoboEvent = "AbfertigungsBefehl"
            Case &H4791
                BoboEvent = "AnzeigeTürenLinks"
            Case &H4787
                BoboEvent = "AnzeigeTürenRechts"
            Case &H4844
                BoboEvent = "AnzeigeTürverriegelung"
            Case &H7990
                BoboEvent = "AnzeigeZugsbeleuchtung"
            Case &H4236
                BoboEvent = "AnzeigeZugsammelSchiene"
            Case &H445C
                BoboEvent = "AnzeigeVentilator"
            Case &H5DC0
                BoboEvent = "SignalInformationZuSi"
            Case Else
                BoboEvent = "Unknown"
        End Select
    End Function
    Private Sub convertBobo2Loksim3d()

    End Sub
    Private Sub convertLoksim3d2Bobo()
        ' later....
    End Sub
    Public Sub SendToSimulator(ByVal byte0 As Byte, ByVal byte1 As Byte, ByVal byte2 As Byte)


        If Form_CommandCenter.CheckBox1.Checked And ((byte0 * 256 + byte1) = 10101 Or (byte0 * 256 + byte1) = 20101) Then
            ' Ignor writing "Druck Events" ...
        Else
            Form_CommandCenter.TextBox1.AppendText(byte0 & "," & byte1 & "," & byte2 & " (" & BoboEvent(byte0, byte1) & ") --> ")

            If Form_CommandCenter.CheckBox3.Checked Then
                MyFile.Write("From Cabine " & Now & " " & byte0 & "," & byte1 & "," & byte2 & " (" & BoboEvent(byte0, byte1) & ") --> ")
            End If
        End If

        ' insert here routine to send appropirate command to the simulator ....

        If Form_CommandCenter.LOCSIM.Checked Then
            'Dim sendBuffer As Byte()
            Dim SendBufferString As String = ""
            'Dim offset As Integer = 0
            'Dim count As Integer = 
            Dim UnknownCommand As Boolean

            'ReDim sendBuffer(10)
            convertBobo2Locsim(BoboEvent(byte0, byte1), byte2, SendBufferString, UnknownCommand)

            If UnknownCommand Then
                ' MsgBox("SendToSimulation got Unkown Comamnd! " & byte0 & "," & byte1 & "," & byte2)
            Else


                If SerialController.SerialPortSimulator.IsOpen Then

                    SerialController.SerialPortSimulator.WriteLine(SendBufferString)

                    If Form_CommandCenter.CheckBox1.Checked And ((byte0 * 256 + byte1) = 10101 Or (byte0 * 256 + byte1) = 20101) Then

                        ' Ignore Writing "Druck Events"
                        'TextBox1.AppendText(byte0 & "," & byte1 & "," & byte2 & " --> ")
                    Else
                        Form_CommandCenter.TextBox1.AppendText(" --> " & SendBufferString & vbCrLf)

                        If Form_CommandCenter.CheckBox3.Checked Then
                            MyFile.Write(" --> " & SendBufferString & vbCrLf)

                        End If

                    End If


                Else
                    MsgBox("SerialPortSimulator is not open!")
                End If
            End If
        ElseIf Form_CommandCenter.LokSim3D.Checked Then
            convertBobo2Loksim3d()
        End If


    End Sub
    Public Sub GetSerialPortNames()
        ' Show all available COM ports.
        For Each sp As String In My.Computer.Ports.SerialPortNames
            Form_CommandCenter.ListBox1.Items.Add(sp)
        Next
    End Sub
    Public Sub CabineInit()
        ' Set Magnet of M-Taste to active
        SendToCabine(&H47, &HE1, 2, 0, 1)
    End Sub
    Public Sub SendToSimulatorUBW_train(ByVal register As String, ByVal port As Integer, ByVal value As Integer)

        Select Case register
            Case "A"
            Case "B"
            Case "C"
            Case "D"
                Select Case port
                    Case 1
                        If value = 0 Then
                            ' pressed
                            SendToSimulator(&H5B, &HCC, 2)
                            UpdateBoBoGUI(&H5B, &HCC, 2)
                        Else
                            ' not pressed (here it actually takes the value of the "port" ! so it is not nessesary "1"!)
                            SendToSimulator(&H5B, &HCC, 1)
                            UpdateBoBoGUI(&H5B, &HCC, 1)
                        End If
                End Select
            Case "E"
                Select Case port
                    Case 3
                        If value = 0 Then
                            Form_PortState.Panel1.BackColor = Color.DarkGreen
                            Form_CommandCenter.Panel1.BackColor = Color.DarkGreen
                        Else
                            Form_PortState.Panel1.BackColor = Color.LightGreen
                            Form_CommandCenter.Panel1.BackColor = Color.LightGreen
                        End If
                End Select
            Case "F"
            Case "G"
        End Select

    End Sub
    Public Sub executeLocSimCommand(ByVal commandstring As String)
        'Dim EventString As String
        'Dim UnkownCommand As Boolean
        'Dim sendBuffer As Byte()

        Dim Typ As String
        Dim Kanal As String
        Dim Daten As String

        If Form_CommandCenter.LOCSIM.Checked Then
            Select Case commandstring

                Case "INI1"
                    ' Init or Reset has happened, Send all Cabine States and end with INI2

                    '...send all values and end it if sending an INI2

                    SentToLocSimAllCabineStates(True)


                    Form_CommandCenter.Label22.Text = "LocSimState: Send and Receive"
                Case "INI2"
                    MsgBox("Error: INI2 received, but from Locsim Simulator....")
                Case "INI7"
                    ' Simulator not overloaded anymmore; send all Cabine States

                    '...send all values
                    Form_CommandCenter.Label22.Text = "LocSimState: Send and Receive"
                Case "INI8"
                    ' Simulator is overloaded, stop sending only recieve

                    Form_CommandCenter.Label22.Text = "LocSimState: Overloaded ... do not send to LocSim, only receive"

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
                                                Form_CommandCenter.Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Form_CommandCenter.Timer00.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer00.Enabled = True
                                                UpdateBoBoGUI(&H23, &HF0, 2)
                                                SendToCabine(&H23, &HF0, 2, 0, 1)

                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Form_CommandCenter.Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Form_CommandCenter.Timer00.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer00.Enabled = True
                                                UpdateBoBoGUI(&H23, &HF0, 1)
                                                SendToCabine(&H23, &HF0, 1, 0, 1)
                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Form_CommandCenter.Timer00.Enabled = False
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
                                                Form_CommandCenter.Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Form_CommandCenter.Timer05.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer05.Enabled = True
                                                UpdateBoBoGUI(&H47, &H91, 2)
                                                SendToCabine(&H47, &H91, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Form_CommandCenter.Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Form_CommandCenter.Timer05.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer05.Enabled = True
                                                UpdateBoBoGUI(&H47, &H91, 1)
                                                SendToCabine(&H47, &H91, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Form_CommandCenter.Timer05.Enabled = False
                                            UpdateBoBoGUI(&H47, &H91, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H47, &H91, CInt("&H" & Daten) + 1, 0, 1)
                                        End If

                                    Case "06"  ' V "AnzeigeTürverriegelung"


                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Form_CommandCenter.Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Form_CommandCenter.Timer06.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer06.Enabled = True
                                                UpdateBoBoGUI(&H48, &H44, 2)
                                                SendToCabine(&H48, &H44, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Form_CommandCenter.Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Form_CommandCenter.Timer06.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer06.Enabled = True
                                                UpdateBoBoGUI(&H48, &H44, 1)
                                                SendToCabine(&H48, &H44, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Form_CommandCenter.Timer06.Enabled = False
                                            UpdateBoBoGUI(&H48, &H44, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H48, &H44, CInt("&H" & Daten) + 1, 0, 1)
                                        End If


                                    Case "07" ' /E> Case "AnzeigeTürenRechts"

                                        If CInt("&H" & Daten) > 1 Then
                                            ' Blink

                                            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                                                ' Einschaltdauer mit On beginnend
                                                Form_CommandCenter.Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                                                Form_CommandCenter.Timer07.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer07.Enabled = True
                                                UpdateBoBoGUI(&H47, &H87, 2)
                                                SendToCabine(&H47, &H87, 2, 0, 1)


                                            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                                                ' Einschaltdauer mit Off beginnend
                                                Form_CommandCenter.Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                                                Form_CommandCenter.Timer07.Tag = "1" & Daten_X & Daten_Y
                                                Form_CommandCenter.Timer07.Enabled = True
                                                UpdateBoBoGUI(&H47, &H87, 1)
                                                SendToCabine(&H47, &H87, 1, 0, 1)

                                            Else
                                                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
                                            End If

                                        Else
                                            ' Not blink
                                            Form_CommandCenter.Timer07.Enabled = False
                                            UpdateBoBoGUI(&H47, &H87, CInt("&H" & Daten) + 1)
                                            SendToCabine(&H47, &H87, CInt("&H" & Daten) + 1, 0, 1)
                                        End If


                                        ' zusi...


                                    Case "08"
                                        ' Lampe M-Taste
                                        ' Ist mit dem Magnet verbunden (HW)
                                        ' --> do nothing 
                                        If Form_CommandCenter.CheckBox3.Checked Then
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
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Received from LocSim Magnet M-Tast; ignore ...  Daten: " & Daten & vbCrLf)
                                        End If
                                    Case "10"
                                        ' Notbremsventil - Annahme = "EPVentil243"
                                        If Form_CommandCenter.CheckBox2.Checked Then
                                            UpdateBoBoGUI(&H5E, &HEC, CInt(Daten) + 1)
                                            SendToCabine(&H5E, &HEC, CInt(Daten) + 1, 0, 1)
                                        End If

                                    Case "11"
                                        ' Quitung Gelb
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            If IgnoreZusiForNow Then
                                                MyFile.Write("Received from LocSim Quitung Gelb - Daten: " & Daten & " but ignoring..." & vbCrLf)
                                            Else
                                                MyFile.Write("Received from LocSim Quitung Gelb - Daten: " & Daten & vbCrLf)
                                            End If

                                        End If


                                        If CInt(Daten) = 1 Then
                                            If IgnoreZusiForNow Then
                                                ' do nothing, ignore locsim blink...
                                                If Form_CommandCenter.CheckBox3.Checked Then
                                                    MyFile.Write("IgnoreZusiForNow is true, therefore do nothing, ignore locsim blink..." & Daten & vbCrLf)
                                                End If
                                            Else

                                                If Form_TrainControl.Button18.Tag = 1 Then

                                                    ' M-Taste Nicht Gedrückt ...
                                                    If Form_CommandCenter.CheckBox3.Checked Then
                                                        MyFile.Write("Got Quittung Gelb from Loksim and M-Taste is NOT gedrückt" & Daten & vbCrLf)
                                                    End If
                                                    ' Make sure we ignore further "quit gelb" for 8 seconds
                                                    IgnoreZusiForNow = True
                                                    Form_CommandCenter.IgnoreZusi.Enabled = True

                                                    ' check if Vorgesorgt:

                                                    If Form_TrainControl.Button25.Tag = 2 Then
                                                        ' Rückstelltaste bereits gedrückt
                                                        SendToCabine(&H5D, &HC0, 5, 0, 1)
                                                        If Form_CommandCenter.CheckBox3.Checked Then
                                                            MyFile.Write("Rückstelltaste bereits gedrückt" & Daten & vbCrLf)
                                                        End If
                                                    Else
                                                        ' Rückstelltaste nicht gedrückt
                                                        SendToCabine(&H5D, &HC0, 6, 0, 1)
                                                        ZusiWarteAufQuittung = True
                                                        If Form_CommandCenter.CheckBox3.Checked Then
                                                            MyFile.Write("Rückstelltaste nicht gedrückt" & Daten & vbCrLf)
                                                        End If
                                                    End If
                                                Else
                                                    ' M-Taste ist gedrückt ...
                                                    SendToCabine(&H5D, &HC0, 3, 0, 1)
                                                    If Form_CommandCenter.CheckBox3.Checked Then
                                                        MyFile.Write("Got Quittung Gelb from Loksim and have M-Taste gedrückt" & Daten & vbCrLf)
                                                    End If
                                                    ' Make sure we ignore further "quit gelb" for 8 seconds
                                                    IgnoreZusiForNow = True
                                                    Form_CommandCenter.IgnoreZusi.Enabled = True
                                                End If
                                            End If

                                        Else
                                            If Form_CommandCenter.CheckBox3.Checked Then
                                                MyFile.Write("CInt(Daten) wurde nicht als 1 interpretiert. Aktueller Daten String ist: " & Daten & vbCrLf)
                                            End If
                                        End If


                                    Case "12"
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Received from LocSim Quitung Rot - Daten: " & Daten & vbCrLf)
                                        End If
                                        ' Quitung Rot
                                        ' Hauptsignal überfahren
                                        If Daten <> 0 Then
                                            If Form_TrainControl.Button18.Tag = 1 Then
                                                SendToCabine(&H5D, &HC0, 1, 0, 1)
                                                If Form_CommandCenter.CheckBox3.Checked Then
                                                    MyFile.Write("Got Quittung Rot from Loksim and have M-Taste NOT gedrückt" & Daten & vbCrLf)
                                                End If
                                            Else
                                                ' M-Taste ist gedrückt ...
                                                SendToCabine(&H5D, &HC0, 3, 0, 1)
                                                If Form_CommandCenter.CheckBox3.Checked Then
                                                    MyFile.Write("Got Quittung Rot from Loksim and have M-Taste gedrückt" & Daten & vbCrLf)
                                                End If
                                            End If

                                        Else
                                            ' Signale Reset!! Zusi zuürcksetzen
                                            SendToCabine(&H5D, &HC0, 0, 0, 1)
                                        End If


                                    Case Else
                                        MsgBox("Unknown Kanal <" & Kanal & "> in in executeLocSimCommand typ U received")
                                End Select
                            Case "V" ' Analog


                                Select Case Kanal

                                    Case "00"
                                        'Tacho "AnzeigGeschwindigkeit"
                                        Dim v As Integer

                                        v = Int(159 * CInt("&H" & Daten) / 65535)
                                        Form_CommandCenter.Label35.Text = v & " / " & Daten
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
                                        UpdateBoBoGUI(&H1C, &HE8, Int(255 * CInt("&H" & Daten) / 65535))
                                        SendToCabine(&H1C, &HE8, Int(255 * CInt("&H" & Daten) / 65535), 0, 1)

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
                                        Form_CommandCenter.TextBox12.AppendText("Batterie Strom: " & Daten & vbCrLf)

                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Batterie Strom: " & Daten & vbCrLf)
                                        End If

                                    Case "05"
                                        Form_CommandCenter.TextBox12.AppendText("Batterie Spannung: " & Daten & vbCrLf)

                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Batterie Spannung: " & Daten & vbCrLf)
                                        End If

                                    Case "06"
                                        Form_CommandCenter.TextBox12.AppendText("Heizstrom:" & Daten & vbCrLf)
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Heizstrom:" & Daten & vbCrLf)
                                        End If
                                    Case "07"
                                        Form_CommandCenter.TextBox12.AppendText("Zylinder: " & Daten & vbCrLf)
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Zylinder: " & Daten & vbCrLf)
                                        End If
                                    Case "08"
                                        Form_CommandCenter.TextBox12.AppendText("Speiseleitung:" & Daten & vbCrLf)
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Speiseleitung:" & Daten & vbCrLf)
                                        End If
                                    Case "09"
                                        Form_CommandCenter.TextBox12.AppendText("Hauptleitung:" & Daten & vbCrLf)
                                        If Form_CommandCenter.CheckBox3.Checked Then
                                            MyFile.Write("Ignored .... " & Now & " Hauptleitung:" & Daten & vbCrLf)
                                        End If
                                    Case "10"
                                        Form_CommandCenter.TextBox12.AppendText("ibrems23:" & Daten & vbCrLf)

                                        'Ampere Meter / "AnzeigeFahrstrom"
                                        UpdateBoBoGUI(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535))
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 0, 1)
                                        SendToCabine(&H1E, &HDC, Int(255 * CInt("&H" & Daten) / 65535), 1, 1)

                                    Case Else
                                        Form_CommandCenter.TextBox12.AppendText("Kanal: " & Kanal & " , Daten: " & Daten & vbCrLf)
                                        If Form_CommandCenter.CheckBox3.Checked Then
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



                    Else
                        Form_CommandCenter.TextBox12.AppendText(commandstring & vbCrLf)
                        'MsgBox("Unkown command in executeLocSimCommand: " & commandstring)
                    End If
            End Select
        Else
            MsgBox("Unkown Simulator Connected to Serial Port...")
        End If

    End Sub
    Private Sub SentToLocSimAllCabineStates(ByVal WithINI2 As Boolean)
        If SerialController.SerialPortSimulator.IsOpen Then

            'SteuerstromSchalter
            SendToSimulator(&H31, &H38, Form_TrainControl.Button1.Tag)
            SendToSimulator(&H31, &H38, Form_TrainControl.Button1.Tag)
            SendToSimulator(&H32, &H64, Form_TrainControl.Button2.Tag)
            SendToSimulator(&H34, &H58, Form_TrainControl.Button3.Tag)
            SendToSimulator(&H79, &H7C, Form_TrainControl.Button5.Tag)
            SendToSimulator(&H42, &H4, Form_TrainControl.Button6.Tag)
            SendToSimulator(&H6D, &HC4, Form_TrainControl.Button10.Tag)
            SendToSimulator(&H47, &H90, Form_TrainControl.Button16.Tag)
            SendToSimulator(&H47, &H18, Form_TrainControl.Button15.Tag)
            SendToSimulator(&H47, &H86, Form_TrainControl.Button14.Tag)
            SendToSimulator(&H47, &HE0, Form_TrainControl.Button18.Tag)
            SendToSimulator(&H27, &HF, Form_TrainControl.Button20.Tag)
            SendToSimulator(&H5B, &HCC, Form_TrainControl.Button19.Tag)
            SendToSimulator(&HE5, &H88, Form_TrainControl.Button25.Tag)
            SendToSimulator(&H36, &HB0, Form_TrainControl.FahrtrichtungsSchalterStelleung)
            SendToSimulator(&H3A, &H98, Form_TrainControl.FahrschalterStellung)
            SendToSimulator(&H49, &HD4, Form_TrainControl.PfeifenKnopfstellung)

            If WithINI2 Then
                SerialController.SerialPortSimulator.WriteLine("INI2")
            End If
        Else
            MsgBox("SerialPortSimulator is not open!")
        End If
    End Sub
    Public Sub CabineSimulator_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs)
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.Label19.Text = "Software Used As Cabine Simulator"
        Else
            Form_CommandCenter.Label19.Text = ""
        End If
    End Sub

    Public Sub RecievedCabineData()
        ' Following Buffer route is build for the "RE 420 Cabine" only !!!!

        Dim buffersize As Integer
        Dim bytesToRead As Integer
        Dim EventBufferCopy(-1) As Integer

        buffersize = SerialController.SerialPortCabine.ReadBufferSize
        bytesToRead = SerialController.SerialPortCabine.BytesToRead
        Dim CurrentEventBufferLength As Integer = CabineEventBuffer.Length

        ' Extend CabineEventBuffer with Bytes to be read
        ReDim Preserve CabineEventBuffer(CurrentEventBufferLength + bytesToRead - 1)

        ' Read the bytes into the event buffer
        For i = 0 To bytesToRead - 1
            CabineEventBuffer(CurrentEventBufferLength + i) = SerialController.SerialPortCabine.ReadByte()
        Next

        Do Until CabineEventBuffer.Length < 5

            ' Only use it if the command ends with two bytes set to zero

            If (CabineEventBuffer(3) = 0) And (CabineEventBuffer(4) = 0) Then
                UpdateBoBoGUI(CabineEventBuffer(0), CabineEventBuffer(1), CabineEventBuffer(2))
                If Form_CommandCenter.CabineSimulator.Checked Then

                Else
                    SendToSimulator(CabineEventBuffer(0), CabineEventBuffer(1), CabineEventBuffer(2))
                End If
                If CabineEventBuffer.Length = 5 Then
                    ReDim CabineEventBuffer(-1)
                Else

                    ' Remove first 5 bytes from the Eventbuffer:
                    '***
                    ' Size EventBufferCopy one byte smaller then CabineEventBuffer
                    ReDim EventBufferCopy(CabineEventBuffer.Length - 6)
                    ' Copy CabineEventBuffer from 5th byte into EventBufferCopy
                    Array.Copy(CabineEventBuffer, 5, EventBufferCopy, 0, EventBufferCopy.Length)
                    ' Size CabineEventBuffer same like EventBufferCopy
                    ReDim CabineEventBuffer(EventBufferCopy.Length - 1)
                    ' Copy EventBufferCopy into CabineEventBuffer
                    Array.Copy(EventBufferCopy, CabineEventBuffer, EventBufferCopy.Length)
                End If
            Else

                'Remove first byte from Eventbuffer:
                '***
                ' Size EventBufferCopy one byte smaller then CabineEventBuffer
                ReDim EventBufferCopy(CabineEventBuffer.Length - 2)
                ' Copy CabineEventBuffer from second byte into EventBufferCopy
                Array.Copy(CabineEventBuffer, 1, EventBufferCopy, 0, EventBufferCopy.Length)
                ' Size CabineEventBuffer same like EventBufferCopy
                ReDim CabineEventBuffer(EventBufferCopy.Length - 1)
                ' Copy EventBufferCopy into CabineEventBuffer
                Array.Copy(EventBufferCopy, CabineEventBuffer, EventBufferCopy.Length)
            End If
        Loop
    End Sub
    Public Sub RecievedSerialTesterData()
        ' Following Buffer route is build for the "RE 420 Cabine" only !!!!

        Dim buffersize As Integer
        Dim bytesToRead As Integer
        Dim EventBufferCopy(-1) As Integer
        Dim readByte As Byte

        buffersize = SerialController.SerialPortTester.ReadBufferSize
        bytesToRead = SerialController.SerialPortTester.BytesToRead
        Dim CurrentEventBufferLength As Integer = bytesToRead - 1


        ' Read the bytes into the event buffer
        For i = 0 To bytesToRead - 1
            readByte = SerialController.SerialPortTester.ReadByte()
            Form_SerialPortTester.TextBox1.AppendText(Chr(readByte) & ",")
            Form_SerialPortTester.TextBox5.AppendText(readByte & ",")
        Next

        Form_SerialPortTester.TextBox1.AppendText(vbCrLf)
        Form_SerialPortTester.TextBox5.AppendText(vbCrLf)

    End Sub
    Public Sub RecievedSimulatorData()
        ' Following Buffer route is build for the "LOCSIM Simulator" only !!!!
        ' insert here route to receive commands from the simulator
        ' Dim ContainsCRorY As Boolean = False
        Dim Pos1, Pos2, Pos As Integer
        Dim bytesToRead As Integer
        Dim StringCommand As String
        Dim readByte As Byte

        bytesToRead = SerialController.SerialPortSimulator.BytesToRead


        ' Read the bytes into the event buffer
        For i = 0 To bytesToRead - 1
            readByte = SerialController.SerialPortSimulator.ReadByte()
            SimulatorStringBuffer = SimulatorStringBuffer & Chr(readByte)
        Next

        Form_CommandCenter.Label23.Text = SimulatorStringBuffer
        Pos1 = InStr(SimulatorStringBuffer, Chr(13))
        Pos2 = InStr(SimulatorStringBuffer, Chr(10))

        ' Check if Carriage Return has been send
        If Pos1 <> 0 Or Pos2 <> 0 Then
            If Pos1 <> 0 Then
                Pos = Pos1
            Else
                Pos = Pos2
            End If
            Form_CommandCenter.TextBox2.AppendText(SimulatorStringBuffer & vbCrLf)

            If Form_CommandCenter.CheckBox3.Checked Then
                MyFile.Write("From LocSim " & Now & " " & SimulatorStringBuffer & vbCrLf)
            End If

            Do Until Pos = 0 'do until no furhter complete command
                StringCommand = Mid(SimulatorStringBuffer, 1, Pos - 1)


                Do Until StringCommand = ""
                    ' check if more then one command before Carriage Return
                    If Mid(StringCommand, 1, 1) = "I" Then
                        executeLocSimCommand(Mid(StringCommand, 1, 4))
                        If StringCommand.Length <= 4 Then
                            StringCommand = ""
                        Else
                            StringCommand = Mid(StringCommand, 5)
                        End If
                    ElseIf Mid(StringCommand, 1, 1) = "X" Then
                        executeLocSimCommand(Mid(StringCommand, 1, 9))
                        If StringCommand.Length <= 9 Then
                            StringCommand = ""
                        Else
                            StringCommand = Mid(StringCommand, 10)
                        End If
                    Else
                        MsgBox("Command " & StringCommand & " receivd from LocSim; probably wrong...")
                    End If

                Loop


                ' remove the command from the SimulatorStringBuffer
                If Pos = SimulatorStringBuffer.Length Then
                    SimulatorStringBuffer = ""
                Else
                    SimulatorStringBuffer = Mid(SimulatorStringBuffer, Pos + 1)
                End If

                Pos = InStr(SimulatorStringBuffer, Chr(13))
            Loop
        End If
    End Sub

End Class
