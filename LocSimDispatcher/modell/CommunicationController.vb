Imports System.IO.Ports

Public Class CommunicationController
    Inherits SimHardware

    Public Delegate Sub rxDataDelegate()

    Public Sub New(ByVal controllerUID, ByVal portName)
        AddcontrollerToPortAssign(controllerUID, portName)
    End Sub

    Public Sub New()

    End Sub

    Public Structure controllerToPortAssignment
        Public controllerUID As String
        Public portName As String
    End Structure

    Private Shared controllerToPortAssignList As New ArrayList()

    Public WithEvents serialPortUBW32_Train As New System.IO.Ports.SerialPort()
    Public WithEvents UBW32_2 As New System.IO.Ports.SerialPort()

    Public WithEvents SerialPortCabine As New System.IO.Ports.SerialPort()
    Public WithEvents SerialPortSimulator As New System.IO.Ports.SerialPort()
    Public WithEvents SerialPortTester As New System.IO.Ports.SerialPort()

    Public WithEvents serialPortInterlocking As New System.IO.Ports.SerialPort()

    Public Sub initSerialPortInterlockingController(ByVal controllerUID As String, ByVal portName As String)

        If serialPortInterlocking.IsOpen Then
            serialPortInterlocking.Close()
        End If

        Try
            With serialPortInterlocking
                .PortName = portName
                .BaudRate = 9600
                .Parity = Parity.None
                .DataBits = 8
                .StopBits = StopBits.One
            End With
            serialPortInterlocking.Open()

            Dim returnPacket As String = ""
            Dim sendPacket As String = "V" & vbLf

            Form_InterlockingControl.textBox_ConsoleOutput.Text += sendPacket + vbCrLf
            Form_InterlockingControl.textBox_ConsoleInput.Text += "-" + vbCrLf

            serialPortInterlocking.WriteLine(sendPacket)

            ' Read the command back
            returnPacket = serialPortInterlocking.ReadLine()

            Form_InterlockingControl.textBox_ConsoleInput.Text += returnPacket + vbCrLf
            Form_InterlockingControl.textBox_ConsoleOutput.Text += "-" + vbCrLf

            ' Get the version
            returnPacket = serialPortInterlocking.ReadLine()

            Form_InterlockingControl.textBox_ConsoleInput.Text += returnPacket + vbCrLf
            Form_InterlockingControl.textBox_ConsoleOutput.Text += "-" + vbCrLf

            ' Read the OK back
            returnPacket = serialPortInterlocking.ReadLine()

            Form_InterlockingControl.textBox_ConsoleInput.Text += returnPacket + vbCrLf
            Form_InterlockingControl.textBox_ConsoleOutput.Text += "-" + vbCrLf

            ' Read the CR/LF back
            returnPacket = serialPortInterlocking.ReadLine()

            Form_InterlockingControl.textBox_ConsoleInput.Text += returnPacket + vbCrLf
            Form_InterlockingControl.textBox_ConsoleOutput.Text += "-" + vbCrLf

        Catch ex As Exception
            MsgBox(ex.ToString)
        End Try

        Form_InterlockingControl.textBox_ConsoleInput.Text += "##################################" + vbCrLf
        Form_InterlockingControl.textBox_ConsoleOutput.Text += "##################################" + vbCrLf

    End Sub

    Shared Sub AddSerialPortNamesTo(ByRef listBox As Windows.Forms.ListBox)
        ' Show all available COM ports.
        For Each sp As String In My.Computer.Ports.SerialPortNames
            listBox.Items.Add(sp)
        Next
    End Sub

    Public Sub AddcontrollerToPortAssign(ByVal controllerUID, ByVal portName)
        Dim controllerAssignmet As CommunicationController.controllerToPortAssignment
        controllerAssignmet.controllerUID = controllerUID
        controllerAssignmet.portName = portName
        controllerToPortAssignList.Add(controllerAssignmet)
    End Sub

    Shared Function getSerialPortName(ByVal controllerUID)
        For Each obj As controllerToPortAssignment In controllerToPortAssignList
            If (obj.portName.Equals(controllerUID)) Then
                Return obj.portName
            End If
        Next
        Return ""
    End Function

End Class
