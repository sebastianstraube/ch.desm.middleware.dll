Imports System
Imports System.IO.Ports
Imports System.Math

Public Class Form_CommandCenter

    Public Delegate Sub rxCabineDataDelegate()
    Public Delegate Sub rxSimulatorDataDelegate()
    Public Delegate Sub rxTesterDataDelegate()

    Public re44 As New GUIController

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        re44.GetSerialPortNames()

        re44.FileString = "DispatcherLogfile" & Now & " .txt"
        re44.FileString = Replace(re44.FileString, ":", "_")
        re44.FileString = Replace(re44.FileString, "/", "_")

        ' Add Event Handler Pointer
        AddHandler CabineSimulator.CheckedChanged, AddressOf re44.CabineSimulator_CheckedChanged
        AddHandler re44.SerialController.SerialPortCabine.DataReceived, AddressOf SerialPortCabine_DataReceived
        AddHandler re44.SerialController.SerialPortSimulator.DataReceived, AddressOf SerialPortSimulator_DataReceived
        AddHandler re44.SerialController.SerialPortTester.DataReceived, AddressOf SerialPortTester_DataReceived

    End Sub
    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        If Button1.Text = "Assign" Then

            If ListBox1.SelectedItem <> "" Then

                ' checkif any other instance is using the selected COM-Port

                If re44.SerialController.SerialPortSimulator.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.SerialPortTester.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.serialPortUBW32_Train.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.UBW32_2.PortName = ListBox1.SelectedItem Then
                    MsgBox(ListBox1.SelectedItem & " is already used by another instance!")
                Else
                    If re44.SerialController.SerialPortCabine.IsOpen Then
                        re44.SerialController.SerialPortCabine.Close()
                    End If

                    Try
                        With re44.SerialController.SerialPortCabine
                            .PortName = ListBox1.SelectedItem
                            .BaudRate = 9600
                            .Parity = Parity.None
                            .DataBits = 8
                            .StopBits = StopBits.One
                        End With
                        re44.SerialController.SerialPortCabine.Open()
                        Button1.Text = "Release"
                        Label3.Text = re44.SerialController.SerialPortCabine.PortName
                        ListBox1.ClearSelected()

                        re44.CabineInit()
                    Catch ex As Exception
                        MsgBox(ex.ToString)
                    End Try

                End If
            End If
        ElseIf Button1.Text = "Release" Then
            If re44.SerialController.SerialPortCabine.IsOpen Then
                re44.SerialController.SerialPortCabine.Close()
            End If
            Button1.Text = "Assign"
            re44.SerialController.SerialPortCabine.PortName = "<none>"
            Label3.Text = re44.SerialController.SerialPortCabine.PortName
        Else
            MsgBox("Program Code Mess in  Private Sub Button1_Click")
        End If


    End Sub
    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        If Button2.Text = "Assign" Then

            If ListBox1.SelectedItem <> "" Then

                ' checkif any other instance is using the selected COM-Port
                If re44.SerialController.SerialPortCabine.PortName = ListBox1.SelectedItem Or re44.SerialController.SerialPortTester.PortName = ListBox1.SelectedItem Or re44.SerialController.serialPortUBW32_Train.PortName = ListBox1.SelectedItem Or re44.SerialController.UBW32_2.PortName = ListBox1.SelectedItem Then
                    MsgBox(ListBox1.SelectedItem & " is already used by another instance!")


                Else
                    If re44.SerialController.SerialPortSimulator.IsOpen Then
                        re44.SerialController.SerialPortSimulator.Close()
                    End If

                    Try
                        With re44.SerialController.SerialPortSimulator
                            .PortName = ListBox1.SelectedItem
                            .BaudRate = 38400
                            .Parity = Parity.None
                            .DataBits = 8
                            .StopBits = StopBits.One
                            .Handshake = Handshake.XOnXOff
                        End With
                        re44.SerialController.SerialPortSimulator.Open()
                        Button2.Text = "Release"
                        Label5.Text = re44.SerialController.SerialPortSimulator.PortName
                        ListBox1.ClearSelected()
                    Catch ex As Exception
                        MsgBox(ex.ToString)
                    End Try

                End If
            End If
        ElseIf Button2.Text = "Release" Then
            If re44.SerialController.SerialPortSimulator.IsOpen Then
                re44.SerialController.SerialPortSimulator.Close()
            End If
            Button2.Text = "Assign"
            re44.SerialController.SerialPortSimulator.PortName = "<none>"
            Label5.Text = re44.SerialController.SerialPortSimulator.PortName
        Else
            MsgBox("Program Code Mess in  Private Sub Button2_Click")
        End If
    End Sub
    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        Dim byte0, byte1, byte2 As Byte
        If TextBox3.Text.Length = 4 And TextBox6.Text.Length = 2 Then
            byte0 = Convert.ToInt32(Mid(TextBox3.Text, 1, 2), 16)
            byte1 = Convert.ToInt32(Mid(TextBox3.Text, 3, 2), 16)
            byte2 = Convert.ToInt32(Mid(TextBox6.Text, 1, 2), 16)
            TextBox7.Text = byte0 & "," & byte1 & "," & byte2
            re44.SendToSimulator(byte0, byte1, byte2)
        Else
            MsgBox("Wrong length in Message!" & vbCrLf & "Current Length of key is: " & TextBox3.Text.Length & " but should be 4 (0x0000)" & vbCrLf & "Current Length of data is: " & TextBox6.Text.Length & " but should be 2 (0x00)")
        End If
    End Sub
    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Dim byte0, byte1, byte2 As Byte
        If TextBox8.Text.Length = 4 And TextBox5.Text.Length = 2 Then
            byte0 = Convert.ToInt32(Mid(TextBox8.Text, 1, 2), 16)
            byte1 = Convert.ToInt32(Mid(TextBox8.Text, 3, 2), 16)
            byte2 = Convert.ToInt32(Mid(TextBox5.Text, 1, 2), 16)
            TextBox4.Text = byte0 & "," & byte1 & "," & byte2
            re44.SendToCabine(byte0, byte1, byte2, TextBox13.Text, TextBox14.Text)
        Else
            MsgBox("Wrong length in Message!" & vbCrLf & "Current Length of key is: " & TextBox8.Text.Length & " but should be 4 (0x0000)" & vbCrLf & "Current Length of data is: " & TextBox5.Text.Length & " but should be 2 (0x00)")
        End If
    End Sub
    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button6.Click
        'Ping Führerstand
        re44.SendToCabine(0, 1, 1, 0, 1)
    End Sub
    Private Sub Button7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button7.Click
        'Reset/Init
        re44.SendToCabine(&HF7, &H65, 0, 0, 1)
    End Sub
    Private Sub Button8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button8.Click
        If Button8.Text = "TiltMode" Then
            re44.SendToCabine(&HF6, &H2D, 1, 0, 1)
            Button8.Text = "SimMode"
        Else
            re44.SendToCabine(&HF6, &H2D, 0, 0, 1)
            Button8.Text = "TiltMode"
        End If
    End Sub
    Private Sub Button9_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button9.Click
        If Button9.Text = "Assign" Then

            If ListBox1.SelectedItem <> "" Then
                ' checkif any other instance is using the selected COM-Port
                If re44.SerialController.SerialPortSimulator.PortName = ListBox1.SelectedItem Or re44.SerialController.SerialPortCabine.PortName = ListBox1.SelectedItem Or re44.SerialController.serialPortUBW32_Train.PortName = ListBox1.SelectedItem Or re44.SerialController.UBW32_2.PortName = ListBox1.SelectedItem Then
                    MsgBox(ListBox1.SelectedItem & " is already used by another instance!")
                Else
                    If re44.SerialController.SerialPortTester.IsOpen Then
                        re44.SerialController.SerialPortTester.Close()
                    End If

                    Try
                        With re44.SerialController.SerialPortTester
                            .PortName = ListBox1.SelectedItem
                            .BaudRate = 38400
                            .Parity = Parity.None
                            .DataBits = 8
                            .StopBits = StopBits.One
                            .Handshake = Handshake.XOnXOff
                        End With
                        re44.SerialController.SerialPortTester.Open()
                        Button9.Text = "Release"
                        Label20.Text = re44.SerialController.SerialPortTester.PortName
                        ListBox1.ClearSelected()
                    Catch ex As Exception
                        MsgBox(ex.ToString)
                    End Try

                End If
            End If
        ElseIf Button9.Text = "Release" Then
            If re44.SerialController.SerialPortTester.IsOpen Then
                re44.SerialController.SerialPortTester.Close()
            End If
            Button9.Text = "Assign"
            re44.SerialController.SerialPortTester.PortName = "<none>"
            Label20.Text = re44.SerialController.SerialPortTester.PortName
        Else
            MsgBox("Program Code Mess in  Private Sub Button9_Click")
        End If

    End Sub
    Private Sub Button10_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button10.Click
        If Button10.Text = "Assign" Then

            If ListBox1.SelectedItem <> "" Then

                ' check if other instance is using this COM port
                If re44.SerialController.SerialPortSimulator.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.SerialPortTester.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.SerialPortCabine.PortName = ListBox1.SelectedItem Then
                    MsgBox(ListBox1.SelectedItem & " is already used by another instance!")

                Else
                    re44.SerialController.initController()

                End If
            End If
        ElseIf Button10.Text = "Release" Then
            If re44.SerialController.serialPortUBW32_Train.IsOpen Then
                re44.SerialController.serialPortUBW32_Train.Close()
            End If
            Button10.Text = "Assign"
            re44.SerialController.serialPortUBW32_Train.PortName = "<none>"
            Label25.Text = re44.SerialController.serialPortUBW32_Train.PortName
        Else
            MsgBox("Program Code Mess in  Private Sub Button10_Click")
        End If
    End Sub
    Private Sub Button11_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button11.Click
        If Button11.Text = "Assign" Then

            If ListBox1.SelectedItem <> "" Then

                ' check if other instance is using this COM port
                If re44.SerialController.SerialPortSimulator.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.SerialPortTester.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.SerialPortCabine.PortName = ListBox1.SelectedItem Or
                    re44.SerialController.serialPortUBW32_Train.PortName = ListBox1.SelectedItem Then
                    MsgBox(ListBox1.SelectedItem & " is already used by another instance!")
                Else
                    If re44.SerialController.UBW32_2.IsOpen Then
                        re44.SerialController.UBW32_2.Close()
                    End If

                    Try
                        With re44.SerialController.UBW32_2
                            .PortName = ListBox1.SelectedItem
                            .BaudRate = 9600
                            .Parity = Parity.None
                            .DataBits = 8
                            .StopBits = StopBits.One
                        End With
                        re44.SerialController.UBW32_2.Open()
                        Button11.Text = "Release"
                        Label27.Text = re44.SerialController.UBW32_2.PortName
                        ListBox1.ClearSelected()
                        'initUBW32_2()
                        ServeUBW32_interlocking.Enabled = True
                    Catch ex As Exception
                        MsgBox(ex.ToString)
                    End Try

                End If
            End If
        ElseIf Button11.Text = "Release" Then
            If re44.SerialController.UBW32_2.IsOpen Then
                re44.SerialController.UBW32_2.Close()
            End If
            Button11.Text = "Assign"
            re44.SerialController.UBW32_2.PortName = "<none>"
            Label27.Text = re44.SerialController.UBW32_2.PortName
        Else
            MsgBox("Program Code Mess in  Private Sub Button11_Click")
        End If
    End Sub
    Private Sub Button12_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button12.Click
        re44.CabineInit()
    End Sub
    Private Sub Button13_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button13.Click
        re44.SendToCabine(&H5D, &HC0, 1, 0, 1)
    End Sub
    Private Sub Button14_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button14.Click
        re44.SendToCabine(&H5D, &HC0, 2, 0, 1)
    End Sub
    Private Sub Button15_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button15.Click
        re44.SendToCabine(&H5D, &HC0, 4, 0, 1)
    End Sub
    Private Sub Button16_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button16.Click
        re44.SendToCabine(&H5D, &HC0, 3, 0, 1)
    End Sub
    Private Sub Button17_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button17.Click
        re44.SendToCabine(&H5D, &HC0, 6, 0, 1)
    End Sub
    Private Sub Button18_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button18.Click
        re44.SendToCabine(&H5D, &HC0, 5, 0, 1)
    End Sub
    Private Sub Button19_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button19.Click
        re44.SendToCabine(&H5D, &HC0, 0, 0, 1)
    End Sub
    Private Sub CheckBox3_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CheckBox3.CheckedChanged



        If CheckBox3.Checked Then

            re44.fso = CreateObject("Scripting.FileSystemObject")
            re44.MyFile = re44.fso.CreateTextFile(re44.FileString, True)
        Else
            re44.MyFile.Close()
            Shell("notepad.exe " & re44.FileString, 1)
        End If


    End Sub
    Private Sub CheckBox4_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles CheckBox4.CheckedChanged
        If CheckBox4.Checked Then
            'Send Totmanpedal pressed

            re44.SendToSimulator(&H7E, &H90, 1)


        Else
            'Send Totmanpedal releases
            re44.SendToSimulator(&H7E, &H90, 0)
        End If
    End Sub
    Private Sub Timer00_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer00.Tick
        Dim Daten_X = Mid(Timer00.Tag, 2, 1)
        Dim Daten_Y = Mid(Timer00.Tag, 3, 1)
        Dim PeriodenPhase = Mid(Timer00.Tag, 1, 1)


        If PeriodenPhase = "1" Then
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 1) * 0.167)
                Timer00.Tag = "1" & Daten_X & Daten_Y
                Timer00.Enabled = True
                re44.UpdateBoBoGUI(&H23, &HF0, 1)
                re44.SendToCabine(&H23, &HF0, 1, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 6) * 0.167)
                Timer00.Tag = "1" & Daten_X & Daten_Y
                Timer00.Enabled = True
                re44.UpdateBoBoGUI(&H23, &HF0, 2)
                re44.SendToCabine(&H23, &HF0, 2, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer00.Tag = "2" & Daten_X & Daten_Y
        Else
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                Timer00.Tag = "1" & Daten_X & Daten_Y
                Timer00.Enabled = True
                re44.UpdateBoBoGUI(&H23, &HF0, 2)
                re44.SendToCabine(&H23, &HF0, 2, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer00.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                Timer00.Tag = "1" & Daten_X & Daten_Y
                Timer00.Enabled = True
                re44.UpdateBoBoGUI(&H23, &HF0, 1)
                re44.SendToCabine(&H23, &HF0, 1, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer00.Tag = "1" & Daten_X & Daten_Y
        End If



    End Sub
    Private Sub Timer05_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer05.Tick
        Dim Daten_X = Mid(Timer05.Tag, 2, 1)
        Dim Daten_Y = Mid(Timer05.Tag, 3, 1)
        Dim PeriodenPhase = Mid(Timer05.Tag, 1, 1)


        If PeriodenPhase = "1" Then
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 1) * 0.167)
                Timer05.Tag = "1" & Daten_X & Daten_Y
                Timer05.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H91, 1)
                re44.SendToCabine(&H47, &H91, 1, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 6) * 0.167)
                Timer05.Tag = "1" & Daten_X & Daten_Y
                Timer05.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H91, 2)
                re44.SendToCabine(&H47, &H91, 2, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer05.Tag = "2" & Daten_X & Daten_Y
        Else
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                Timer05.Tag = "1" & Daten_X & Daten_Y
                Timer05.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H91, 2)
                re44.SendToCabine(&H47, &H91, 2, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer05.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                Timer05.Tag = "1" & Daten_X & Daten_Y
                Timer05.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H91, 1)
                re44.SendToCabine(&H47, &H91, 1, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer05.Tag = "1" & Daten_X & Daten_Y
        End If
    End Sub
    Private Sub Timer06_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer06.Tick
        Dim Daten_X = Mid(Timer06.Tag, 2, 1)
        Dim Daten_Y = Mid(Timer06.Tag, 3, 1)
        Dim PeriodenPhase = Mid(Timer06.Tag, 1, 1)


        If PeriodenPhase = "1" Then
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 1) * 0.167)
                Timer06.Tag = "1" & Daten_X & Daten_Y
                Timer06.Enabled = True
                re44.UpdateBoBoGUI(&H48, &H44, 1)
                re44.SendToCabine(&H48, &H44, 1, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 6) * 0.167)
                Timer06.Tag = "1" & Daten_X & Daten_Y
                Timer06.Enabled = True
                re44.UpdateBoBoGUI(&H48, &H44, 2)
                re44.SendToCabine(&H48, &H44, 2, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer06.Tag = "2" & Daten_X & Daten_Y
        Else
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                Timer06.Tag = "1" & Daten_X & Daten_Y
                Timer06.Enabled = True
                re44.UpdateBoBoGUI(&H48, &H44, 2)
                re44.SendToCabine(&H48, &H44, 2, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer06.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                Timer06.Tag = "1" & Daten_X & Daten_Y
                Timer06.Enabled = True
                re44.UpdateBoBoGUI(&H48, &H44, 1)
                re44.SendToCabine(&H48, &H44, 1, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer06.Tag = "1" & Daten_X & Daten_Y
        End If
    End Sub
    Private Sub Timer07_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer07.Tick
        Dim Daten_X = Mid(Timer07.Tag, 2, 1)
        Dim Daten_Y = Mid(Timer07.Tag, 3, 1)
        Dim PeriodenPhase = Mid(Timer07.Tag, 1, 1)


        If PeriodenPhase = "1" Then
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 1) * 0.167)
                Timer07.Tag = "1" & Daten_X & Daten_Y
                Timer07.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H87, 1)
                re44.SendToCabine(&H47, &H87, 1, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (1 - (CInt("&H" & Daten_Y) - 6) * 0.167)
                Timer07.Tag = "1" & Daten_X & Daten_Y
                Timer07.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H87, 2)
                re44.SendToCabine(&H47, &H87, 2, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer07.Tag = "2" & Daten_X & Daten_Y
        Else
            If (CInt("&H" & Daten_Y) >= 2) And (CInt("&H" & Daten_Y) < 7) Then
                ' Einschaltdauer mit On beginnend
                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 1) * 0.167
                Timer07.Tag = "1" & Daten_X & Daten_Y
                Timer07.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H87, 2)
                re44.SendToCabine(&H47, &H87, 2, 0, 1)

            ElseIf (CInt("&H" & Daten_Y) >= 7) And (CInt("&H" & Daten_Y) < 12) Then
                ' Einschaltdauer mit Off beginnend
                Timer07.Interval = (CInt("&H" & Daten_X) + 1) * 250 * (CInt("&H" & Daten_Y) - 6) * 0.167
                Timer07.Tag = "1" & Daten_X & Daten_Y
                Timer07.Enabled = True
                re44.UpdateBoBoGUI(&H47, &H87, 1)
                re44.SendToCabine(&H47, &H87, 1, 0, 1)
            Else
                MsgBox("executeLocSimCommand got blink data with Y value=" & Daten_Y & "what is not clear in locsim definition")
            End If

            Timer07.Tag = "1" & Daten_X & Daten_Y
        End If
    End Sub
    Private Sub ServeUBW32Tick_train(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ServeUBW32_train.Tick
        ServeUBW32_train.Enabled = False
        ' This routine handles following:
        ' 1. Read all register (to capture state of digital inputs)
        ' 2. Creates all digital commands for the simulation ()
        ' 2. Sets all output values (digital and analog) from the "UBW32_1_Queue"
        ' 3. Checks if there is a digital input change by comparing old register values with the once read under step 1. above
        ' 4. Creates digital Commands for Simulation
        ' 5. Creates Analog Commands for simuluation

        Dim SendToCabineQueue(-1) As GUIController.CabineCommand
        Dim SendToSimulatorQueue(-1) As UBW32.UBWdigitalInput
        Dim PortArray() As String
        Dim ChangedBitsValue As Integer
        Dim ReturnPacket As String
        Dim Sent As Boolean = False

        ' ReadFromCabine
        If re44.SerialController.serialPortUBW32_Train.IsOpen Then

            ' Read Analog ports

            re44.SerialController.serialPortUBW32_Train.WriteLine("IA," & re44.SerialController.pinDefinition(re44.SerialController.pinDefinition.Length - 1).AnalogPinMask & ",10,1" & vbLf)
            'Read Command Back
            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
            ' Read IA ports
            re44.SerialController.analogIN = re44.SerialController.serialPortUBW32_Train.ReadLine()
            ' Read OK back
            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
            ' Read CR/LF back
            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()


            PortArray = Split(re44.SerialController.analogIN, ",")
            re44.SerialController.AN0_Current = PortArray(1)
            re44.SerialController.AN1_Current = PortArray(2)



            ' Update Buffer
            re44.SerialController.AN0_Buffer(re44.SerialController.AN0_BufferIndex) = re44.SerialController.AN0_Current
            re44.SerialController.AN0_BufferIndex = re44.SerialController.AN0_BufferIndex + 1
            If re44.SerialController.AN0_BufferIndex > UBW32.UBS32_Analog_AN0_Average Then
                re44.SerialController.AN0_BufferIndex = 0
            End If
            re44.SerialController.AN1_Buffer(re44.SerialController.AN1_BufferIndex) = re44.SerialController.AN1_Current
            re44.SerialController.AN1_BufferIndex = re44.SerialController.AN1_BufferIndex + 1
            If re44.SerialController.AN1_BufferIndex > UBW32.UBS32_Analog_AN1_Average Then
                re44.SerialController.AN1_BufferIndex = 0
            End If



            ' Calculate Average
            Dim SumAN0 As Integer
            For i As Integer = 0 To UBW32.UBS32_Analog_AN0_Average
                SumAN0 = SumAN0 + re44.SerialController.AN0_Buffer(i)
            Next
            Dim SumAN1 As Integer
            For i As Integer = 0 To UBW32.UBS32_Analog_AN1_Average
                SumAN1 = SumAN1 + re44.SerialController.AN1_Buffer(i)
            Next



            ' Validate against hysterese
            If Abs((SumAN0 / (UBW32.UBS32_Analog_AN0_Average + 1)) - re44.SerialController.AN0_Average) < UBW32.UBW32_1_AN0_Hysterese Then
                ' Change less then hysterese, do nothing
            Else
                ' Change more then hsystrere, create the even!
                re44.SerialController.AN0_Average = SumAN0 / (UBW32.UBS32_Analog_AN0_Average + 1)
                ' Bremszylinder Druck
                Dim BZ_Druck_Convertion As Byte
                BZ_Druck_Convertion = re44.SerialController.AN0_Average / 4
                re44.SendToSimulator(&H4E, &H85, BZ_Druck_Convertion)


            End If
            If Abs((SumAN1 / (UBW32.UBS32_Analog_AN1_Average + 1)) - re44.SerialController.AN1_Average) < UBW32.UBW32_1_AN1_Hysterese Then
                ' Change less then hysterese, do nothing
            Else
                ' Change more then hsystrere, create the even!
                re44.SerialController.AN1_Average = SumAN1 / (UBW32.UBS32_Analog_AN1_Average + 1)
                ' Hauptleitungs Druck
                Dim HL_Druck_Convertion As Byte
                HL_Druck_Convertion = re44.SerialController.AN1_Average / 4
                re44.SendToSimulator(&H27, &H75, HL_Druck_Convertion)
            End If

            Form_PortState.Label3.Text = re44.SerialController.AN0_Current & " (" & re44.SerialController.AN0_Average & ")"
            Form_PortState.Label4.Text = re44.SerialController.AN1_Current & " (" & re44.SerialController.AN1_Average & ")"

            ' Read digital ports (all register)
            re44.SerialController.serialPortUBW32_Train.WriteLine("I" & vbLf)
            re44.SerialController.serialPortUBW32_Train.ReadLine()
            re44.SerialController.PortString = re44.SerialController.serialPortUBW32_Train.ReadLine()
            re44.SerialController.serialPortUBW32_Train.ReadLine()
            re44.SerialController.serialPortUBW32_Train.ReadLine()

            ' PortStatus = I,<StatusA>,<StatusB>,<StatusC>,<StatusD>,<StatusE>,<StatusF>,<StatusG>
            PortArray = Split(re44.SerialController.PortString, ",")
            Form_PortState.Label1A.Text = "PortA:" & PortArray(1)
            Form_PortState.Label1B.Text = "PortB:" & PortArray(2)
            Form_PortState.Label1C.Text = "PortC:" & PortArray(3)
            Form_PortState.Label1D.Text = "PortD:" & PortArray(4)
            Form_PortState.Label1E.Text = "PortE:" & PortArray(5)
            Form_PortState.Label1F.Text = "PortF:" & PortArray(6)
            Form_PortState.Label1G.Text = "PortG:" & PortArray(7)

            ' Check all the ports which changed
            If re44.SerialController.Register_A <> Int(PortArray(1)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "A"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_A
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(1))
                re44.SerialController.Register_A = Int(PortArray(1))
            End If
            If re44.SerialController.Register_B <> Int(PortArray(2)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "B"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_B
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(2))
                re44.SerialController.Register_B = Int(PortArray(2))
            End If
            If re44.SerialController.Register_C <> Int(PortArray(3)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "C"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_C
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(3))
                re44.SerialController.Register_C = Int(PortArray(3))
            End If
            If re44.SerialController.Register_D <> Int(PortArray(4)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "D"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_D
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(4))
                re44.SerialController.Register_D = Int(PortArray(4))
            End If
            If re44.SerialController.Register_E <> Int(PortArray(5)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "E"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_E
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(5))
                re44.SerialController.Register_E = Int(PortArray(5))
            End If
            If re44.SerialController.Register_F <> Int(PortArray(6)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "F"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_F
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(6))
                re44.SerialController.Register_F = Int(PortArray(6))
            End If
            If re44.SerialController.Register_G <> Int(PortArray(7)) Then
                ReDim Preserve SendToSimulatorQueue(SendToSimulatorQueue.Length)
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).Register = "G"
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).OldValue = re44.SerialController.Register_G
                SendToSimulatorQueue(SendToSimulatorQueue.Length - 1).NewValue = Int(PortArray(7))
                re44.SerialController.Register_G = Int(PortArray(7))
            End If

        End If

        ' Sendto Cabine
        If re44.UBW32_1_Queue.Length > 0 Then
            ' copy buffer and squeeze global Array
            ReDim SendToCabineQueue(re44.UBW32_1_Queue.Length - 1)
            Array.Copy(re44.UBW32_1_Queue, SendToCabineQueue, re44.UBW32_1_Queue.Length)
            ReDim re44.UBW32_1_Queue(-1)

            ' set all the appropriate Output ports
            If re44.SerialController.serialPortUBW32_Train.IsOpen Then
                For i As Integer = 0 To SendToCabineQueue.Length - 1
                    ' set output on UBW32
                    Select Case SendToCabineQueue(i).command
                        Case 2
                            ' Test Command Digital Out on UBW32
                            MsgBox("UBW32 Digital Out Test Command: " & SendToCabineQueue(i).command & "," & SendToCabineQueue(i).value)
                        Case 3
                            ' Test Command Analog Out on UBW32
                            MsgBox("UBW32 Analog Out Test Command: " & SendToCabineQueue(i).command & "," & SendToCabineQueue(i).value)
                        Case 16300
                            ' Stufenschaltermeldelampe als test auf LED-1 on UBW32 (RE2)
                            If SendToCabineQueue(i).value = 1 Then
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,2,1" & vbLf)
                            Else
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,2,0" & vbLf)
                            End If
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the OK back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the CR/LF back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                        Case 16950
                            ' Zugsammelschine lampe als test auf LED-2 on UBW32 (RE1)
                            If SendToCabineQueue(i).value = 1 Then
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,1,1" & vbLf)
                            Else
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,1,0" & vbLf)
                            End If
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the OK back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the CR/LF back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                        Case 18100
                            ' Abfertings Befehl als test auf LED-3 on UBW32 (RE0)
                            If SendToCabineQueue(i).value = 1 Then
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,0,1" & vbLf)
                            Else
                                re44.SerialController.serialPortUBW32_Train.WriteLine("PO,E,0,0" & vbLf)
                            End If
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the OK back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the CR/LF back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                        Case 7900
                            ' Fahrstrom to Analog output

                            'MsgBox(SendToCabineQueue(i).value)
                            ' Use here PM or SP&PC command....

                            ' Fahrstrom is on RD0 --> use port + 1 as the value!
                            re44.SerialController.serialPortUBW32_Train.WriteLine("PM,1," & SendToCabineQueue(i).value * 256 & vbLf)
                            ' Read the command back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the OK back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()
                            ' Read the CR/LF back
                            ReturnPacket = re44.SerialController.serialPortUBW32_Train.ReadLine()


                            'Sent = True

                        Case Else
                            MsgBox("Unknown Command to be send in ServeUBW32_1_Tick: " & SendToCabineQueue(i).command)
                    End Select

                Next
            End If

        End If

        ' Send all digital commands to the Simulator
        For i As Integer = 0 To SendToSimulatorQueue.Length - 1
            ' identify which bit(s) did change
            ChangedBitsValue = SendToSimulatorQueue(i).OldValue Xor SendToSimulatorQueue(i).NewValue

            If ChangedBitsValue And UBW32.bit0 Then
                ' bit0 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 0, SendToSimulatorQueue(i).NewValue And UBW32.bit0)
            End If
            If ChangedBitsValue And UBW32.bit1 Then
                ' bit1 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 1, SendToSimulatorQueue(i).NewValue And UBW32.bit1)
            End If
            If ChangedBitsValue And UBW32.bit2 Then
                ' bit2 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 2, SendToSimulatorQueue(i).NewValue And UBW32.bit2)
            End If
            If ChangedBitsValue And UBW32.bit3 Then
                ' bit3 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 3, SendToSimulatorQueue(i).NewValue And UBW32.bit3)
            End If
            If ChangedBitsValue And UBW32.bit4 Then
                ' bit4 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 4, SendToSimulatorQueue(i).NewValue And UBW32.bit4)
            End If
            If ChangedBitsValue And UBW32.bit5 Then
                ' bit5 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 5, SendToSimulatorQueue(i).NewValue And UBW32.bit5)
            End If
            If ChangedBitsValue And UBW32.bit6 Then
                ' bit6 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 6, SendToSimulatorQueue(i).NewValue And UBW32.bit6)
            End If
            If ChangedBitsValue And UBW32.bit7 Then
                ' bit7 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 7, SendToSimulatorQueue(i).NewValue And UBW32.bit7)
            End If
            If ChangedBitsValue And UBW32.bit8 Then
                ' bit8 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 8, SendToSimulatorQueue(i).NewValue And UBW32.bit8)
            End If
            If ChangedBitsValue And UBW32.bit9 Then
                ' bit9 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 9, SendToSimulatorQueue(i).NewValue And UBW32.bit9)
            End If
            If ChangedBitsValue And UBW32.bit10 Then
                ' bit10 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 10, SendToSimulatorQueue(i).NewValue And UBW32.bit10)
            End If
            If ChangedBitsValue And UBW32.bit11 Then
                ' bit11 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 11, SendToSimulatorQueue(i).NewValue And UBW32.bit11)
            End If
            If ChangedBitsValue And UBW32.bit12 Then
                ' bit12 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 12, SendToSimulatorQueue(i).NewValue And UBW32.bit12)
            End If
            If ChangedBitsValue And UBW32.bit13 Then
                ' bit13 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 13, SendToSimulatorQueue(i).NewValue And UBW32.bit13)
            End If
            If ChangedBitsValue And UBW32.bit14 Then
                ' bit14 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 14, SendToSimulatorQueue(i).NewValue And UBW32.bit14)
            End If
            If ChangedBitsValue And UBW32.bit15 Then
                ' bit15 has changed!
                re44.SendToSimulatorUBW_train(SendToSimulatorQueue(i).Register, 15, SendToSimulatorQueue(i).NewValue And UBW32.bit15)
            End If

        Next
        ServeUBW32_train.Enabled = True
    End Sub
    Private Sub ServeUBW32_2_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ServeUBW32_interlocking.Tick


    End Sub
    Private Sub FührerstandsGUIToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles FührerstandsGUIToolStripMenuItem.Click
        Form_TrainControl.Show()
    End Sub
    Private Sub SerialPortTesterToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles SerialPortTesterToolStripMenuItem.Click
        Form_SerialPortTester.Show()
    End Sub
    Private Sub UBWPortstatsToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles UBWPortstatsToolStripMenuItem.Click
        Form_PortState.Show()
    End Sub
    Private Sub IgnoreZusi_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles IgnoreZusi.Tick
        IgnoreZusi.Enabled = False
        re44.IgnoreZusiForNow = False
    End Sub
    Private Sub SerialPortCabine_DataReceived(ByVal sender As System.Object, ByVal e As System.IO.Ports.SerialDataReceivedEventArgs)
        Me.Invoke(New rxCabineDataDelegate(AddressOf re44.RecievedCabineData), New Object() {})
    End Sub
    Private Sub SerialPortSimulator_DataReceived(ByVal sender As System.Object, ByVal e As System.IO.Ports.SerialDataReceivedEventArgs)
        Me.Invoke(New rxSimulatorDataDelegate(AddressOf re44.RecievedSimulatorData), New Object() {})
    End Sub
    Private Sub SerialPortTester_DataReceived(ByVal sender As System.Object, ByVal e As System.IO.Ports.SerialDataReceivedEventArgs)
        Me.Invoke(New rxTesterDataDelegate(AddressOf re44.RecievedSerialTesterData), New Object() {})
    End Sub

End Class
