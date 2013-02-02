Public Class Form_MainControl

    Public guiController As New GUIController

    Declare Sub stw_initialize Lib "LocSimDispatcherPlugin.dll" Alias "initialize" ()

    Private Sub Form_MainControl_Load(sender As System.Object, e As System.EventArgs) Handles MyBase.Load

        REM *DLL CALL*
        REM Call stw_initialize()

    End Sub

    Private Sub btn_commandCenter_Click(sender As System.Object, e As System.EventArgs) Handles btn_commandCenter.Click
        Form_CommandCenter.Show()
    End Sub

    Private Sub btn_trainControl_Click(sender As System.Object, e As System.EventArgs) Handles btn_trainControl.Click
        Form_TrainControl.Show()
    End Sub

    Private Sub btn_portTester_Click(sender As System.Object, e As System.EventArgs) Handles btn_portTester.Click
        Form_SerialPortTester.Show()
    End Sub

    Private Sub btn_portStatus_Click(sender As System.Object, e As System.EventArgs) Handles btn_portStatus.Click
        Form_PortState.Show()
    End Sub

    Private Sub btn_InterlockingControl_Click(sender As System.Object, e As System.EventArgs) Handles btn_InterlockingControl.Click
        Form_InterlockingControl.Show()
    End Sub
End Class