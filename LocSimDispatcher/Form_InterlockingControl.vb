Public Class Form_InterlockingControl

    Public StellwerkLangnauObermattElements As New IOElement

    Private Sub Form_InterlockingCommandCenter_Load(sender As System.Object, e As System.EventArgs) Handles MyBase.Load
        CommunicationController.AddSerialPortNamesTo(ListBox_ComPorts)
    End Sub

    Private Sub btn_assignControllerToPort_Click(sender As System.Object, e As System.EventArgs) Handles btn_assignControllerToPort.Click
        StellwerkLangnauObermattElements.communicationControl.AddcontrollerToPortAssign(UBW32_interlocking.UIDHash, ListBox_ComPorts.SelectedItem)
        StellwerkLangnauObermattElements.communicationControl.initSerialPortInterlockingController(UBW32_interlocking.UIDHash, ListBox_ComPorts.SelectedItem)


        Dim configuration As New UBW32_interlocking.Command
        configuration.Description = "Konfiguration"
        configuration.IOType = UBW32_interlocking.IOTYPE_CONFIGURATION
        configuration.Register.A = 14784
        configuration.Register.B = 199
        configuration.Register.C = 65505
        configuration.Register.D = 16383
        configuration.Register.E = 64528
        configuration.Register.F = 52939
        configuration.Register.G = 64575

        StellwerkLangnauObermattElements.sendCommand(configuration)

    End Sub

    Private Sub textBox_Console_TextChanged(sender As System.Object, e As System.EventArgs) Handles textBox_ConsoleOutput.TextChanged
        textBox_ConsoleOutput.ForeColor = Color.Green
    End Sub



    Private Sub btn_sendConfiguraion_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario1.Click

        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario1"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 9224
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 0
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)

    End Sub

    Private Sub btn_SendSceanrio2_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendSceanrio2.Click

        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario2"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 25096
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 0
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)

    End Sub

    Private Sub btn_SendScenario3_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario3.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario3"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 25096
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 256
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendScenario4_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario4.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario4"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32800
        scenario.Register.B = 25096
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 288
        scenario.Register.G = 768

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendScenario5_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario5.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario5"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32800
        scenario.Register.B = 25608
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 292
        scenario.Register.G = 768

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SndScenario6_Click(sender As System.Object, e As System.EventArgs) Handles btn_SndScenario6.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario6"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32800
        scenario.Register.B = 25608
        scenario.Register.C = 0
        scenario.Register.D = 16384
        scenario.Register.E = 0
        scenario.Register.F = 36
        scenario.Register.G = 768

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendSceanrio7_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendSceanrio7.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario7"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 50184
        scenario.Register.C = 0
        scenario.Register.D = 16384
        scenario.Register.E = 0
        scenario.Register.F = 32
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendScenario8_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario8.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Scenario8"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 50184
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 32
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendScenario9_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario9.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Konfiguration"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 17416
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 32
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private Sub btn_SendScenario10_Click(sender As System.Object, e As System.EventArgs) Handles btn_SendScenario10.Click
        Dim scenario As New UBW32_interlocking.Command
        scenario.Description = "Konfiguration"
        scenario.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        scenario.Register.A = 32801
        scenario.Register.B = 17416
        scenario.Register.C = 0
        scenario.Register.D = 0
        scenario.Register.E = 0
        scenario.Register.F = 0
        scenario.Register.G = 256

        StellwerkLangnauObermattElements.sendCommand(scenario)
    End Sub

    Private btn_FSSSperrmagnet_Clicked As Boolean = False
    Private Sub btn_FSSSperrmagnet_Click(sender As System.Object, e As System.EventArgs) Handles btn_FSSSperrmagnet.Click

        If (Not btn_FSSSperrmagnet_Clicked) Then
            btn_FSSSperrmagnet.BackColor = Color.Gold
            StellwerkLangnauObermattElements.sendFSSSperrmagnet(True)
            btn_FSSSperrmagnet_Clicked = True
        Else
            btn_FSSSperrmagnet.BackColor = Color.Gray
            StellwerkLangnauObermattElements.sendFSSSperrmagnet(False)
            btn_FSSSperrmagnet_Clicked = False
        End If


    End Sub

    Private btn_FSSKuppelstrommagnet_CLicked As Boolean = False
    Private Sub btn_FSSKuppelstrommagnet_Click(sender As System.Object, e As System.EventArgs) Handles btn_FSSKuppelstrommagnet.Click


        If (Not btn_FSSKuppelstrommagnet_CLicked) Then
            btn_FSSKuppelstrommagnet.BackColor = Color.Gold
            StellwerkLangnauObermattElements.sendFSSKuppelstrommagnet(True)
            btn_FSSKuppelstrommagnet_CLicked = True
        Else
            btn_FSSKuppelstrommagnet.BackColor = Color.Gray
            StellwerkLangnauObermattElements.sendFSSKuppelstrommagnet(False)
            btn_FSSKuppelstrommagnet_CLicked = False
        End If
    End Sub
End Class