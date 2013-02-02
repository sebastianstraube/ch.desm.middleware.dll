Imports System.IO.Ports

Public Class UBW32_interlocking

    Public WithEvents communicationControl As New CommunicationController()

    Private Sub SerialPortInterlocking_DataReceived(ByVal sender As System.Object, ByVal e As System.IO.Ports.SerialDataReceivedEventArgs)
        Form_InterlockingControl.Invoke(New CommunicationController.rxDataDelegate(AddressOf ReceivedInterlockingData), New Object() {})
    End Sub

    Public Const UID As String = "C_INTERLOCKING_1"
    'http://www.rene-ade.de/inhalte/md5-hash-wert.html
    Public Const UIDHash As String = "a8e4263db47eab5aef727a31f99570a6"

    Public Const COMMAND_STATUS_SEND As Integer = 1
    Public Const COMMAND_STATUS_WAIT As Integer = 2
    Public Const COMMAND_STATUS_OK As Integer = 3
    Public Const COMMAND_STATUS_NOK As Integer = 4


    Public Const Register_A As Integer = 1
    Public Const Register_B As Integer = 2
    Public Const Register_C As Integer = 3
    Public Const Register_D As Integer = 4
    Public Const Register_E As Integer = 5
    Public Const Register_F As Integer = 6
    Public Const Register_G As Integer = 7

    Public Const PIN_OUTPUT_DISABLE = 0
    Public Const PIN_INPUT_DISABLE = 65535
    Public Const PIN0_OUTPUT_ENABLE As Integer = 1
    Public Const PIN1_OUTPUT_ENABLE As Integer = 2
    Public Const PIN2_OUTPUT_ENABLE As Integer = 4
    Public Const PIN3_OUTPUT_ENABLE As Integer = 8
    Public Const PIN4_OUTPUT_ENABLE As Integer = 16
    Public Const PIN5_OUTPUT_ENABLE As Integer = 32
    Public Const PIN6_OUTPUT_ENABLE As Integer = 64
    Public Const PIN7_OUTPUT_ENABLE As Integer = 128
    Public Const PIN8_OUTPUT_ENABLE As Integer = 256
    Public Const PIN9_OUTPUT_ENABLE As Integer = 512
    Public Const PIN10_OUTPUT_ENABLE As Integer = 1024
    Public Const PIN11_OUTPUT_ENABLE As Integer = 2048
    Public Const PIN12_OUTPUT_ENABLE As Integer = 4096
    Public Const PIN13_OUTPUT_ENABLE As Integer = 8192
    Public Const PIN14_OUTPUT_ENABLE As Integer = 16384
    Public Const PIN15_OUTPUT_ENABLE As Integer = 32768

    Public Const PIN0_OUTPUT_DISABLE As Integer = -1
    Public Const PIN1_OUTPUT_DISABLE As Integer = -2
    Public Const PIN2_OUTPUT_DISABLE As Integer = -4
    Public Const PIN3_OUTPUT_DISABLE As Integer = -8
    Public Const PIN4_OUTPUT_DISABLE As Integer = -16
    Public Const PIN5_OUTPUT_DISABLE As Integer = -32
    Public Const PIN6_OUTPUT_DISABLE As Integer = -64
    Public Const PIN7_OUTPUT_DISABLE As Integer = -128
    Public Const PIN8_OUTPUT_DISABLE As Integer = -256
    Public Const PIN9_OUTPUT_DISABLE As Integer = -512
    Public Const PIN10_OUTPUT_DISABLE As Integer = -1024
    Public Const PIN11_OUTPUT_DISABLE As Integer = -2048
    Public Const PIN12_OUTPUT_DISABLE As Integer = -4096
    Public Const PIN13_OUTPUT_DISABLE As Integer = -8192
    Public Const PIN14_OUTPUT_DISABLE As Integer = -16384
    Public Const PIN15_OUTPUT_DISABLE As Integer = -32768

    Public Const IOTYPE_INPUT As String = "I"
    Public Const IOTYPE_OUTPUT As String = "O"
    Public Const IOTYPE_CONFIGURATION As String = "C"
    Private Const COMMAND_SEPERATOR As String = ","

    Public Structure Register
        Public A As Integer
        Public B As Integer
        Public C As Integer
        Public D As Integer
        Public E As Integer
        Public F As Integer
        Public G As Integer
    End Structure

    Public Structure Pins
        Public pin0 As Integer
        Public pin1 As Integer
        Public pin2 As Integer
        Public pin3 As Integer
        Public pin4 As Integer
        Public pin5 As Integer
        Public pin6 As Integer
        Public pin7 As Integer
        Public pin8 As Integer
        Public pin9 As Integer
        Public pin10 As Integer
        Public pin11 As Integer
        Public pin12 As Integer
        Public pin13 As Integer
        Public pin14 As Integer
        Public pin15 As Integer
    End Structure

    Public Structure IOPort
        Public Description As String
        Public IOType As String
        Public register As Register
        Public pins As Pins
    End Structure

    Public Structure Command
        Public Description As String
        Public IOType As String
        Public Register As Register
        Public PostChar As String
        Public Status As String
    End Structure

    Private aktCommand As Command
    Private aktRegister As Register
    Private aktIOType As String
    Private controllerVersion As String

    'ToDo
    Public Sub addIOPortTo(io As String, register As Integer, pin0 As Integer, pin1 As Integer, pin2 As Integer, pin3 As Integer, pin4 As Integer, pin5 As Integer, pin6 As Integer, pin7 As Integer, pin8 As Integer, pin9 As Integer, pin10 As Integer, pin11 As Integer, pin12 As Integer, pin13 As Integer, pin14 As Integer, pin15 As Integer)

    End Sub

    Private actualCommand As New Command

    Public Sub addIOPortToActualCommand(ByVal port As IOPort)
        actualCommand.Description += " [" + port.Description + "-" + port.IOType + "]"

        Dim portSummary As String

        portSummary = port.pins.pin0 + port.pins.pin1 + port.pins.pin2 + port.pins.pin3 + port.pins.pin4 + port.pins.pin5 + port.pins.pin6 + port.pins.pin7 + port.pins.pin8 + port.pins.pin9 + port.pins.pin10 + port.pins.pin11 + port.pins.pin12 + port.pins.pin13 + port.pins.pin14 + port.pins.pin15

        If (actualCommand.IOType = UBW32_interlocking.IOTYPE_OUTPUT) Then
            If (port.register.A = Register_A) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.A -= portSummary
                Else
                    actualCommand.Register.A += portSummary
                End If
            ElseIf (port.register.B = Register_B) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.B -= portSummary
                Else
                    actualCommand.Register.B += portSummary
                End If
            ElseIf (port.register.C = Register_C) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.C -= portSummary
                Else
                    actualCommand.Register.C += portSummary
                End If
            ElseIf (port.register.D = Register_D) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.D -= portSummary
                Else
                    actualCommand.Register.D += portSummary
                End If
            ElseIf (port.register.E = Register_E) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.E -= portSummary
                Else
                    actualCommand.Register.E += portSummary
                End If
            ElseIf (port.register.F = Register_F) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.F -= portSummary
                Else
                    actualCommand.Register.F += portSummary
                End If
            ElseIf (port.register.G = Register_G) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_INPUT)) Then
                    actualCommand.Register.G -= portSummary
                Else
                    actualCommand.Register.G += portSummary
                End If
            End If
        ElseIf (actualCommand.IOType = UBW32_interlocking.IOTYPE_INPUT) Then
            If (port.register.A = Register_A) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.A -= portSummary
                Else
                    actualCommand.Register.A += portSummary
                End If
            ElseIf (port.register.B = Register_B) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.B -= portSummary
                Else
                    actualCommand.Register.B += portSummary
                End If
            ElseIf (port.register.C = Register_C) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.C -= portSummary
                Else
                    actualCommand.Register.C += portSummary
                End If
            ElseIf (port.register.D = Register_D) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.D -= portSummary
                Else
                    actualCommand.Register.D += portSummary
                End If
            ElseIf (port.register.E = Register_E) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.E -= portSummary
                Else
                    actualCommand.Register.E += portSummary
                End If
            ElseIf (port.register.F = Register_F) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.F -= portSummary
                Else
                    actualCommand.Register.F += portSummary
                End If
            ElseIf (port.register.G = Register_G) Then
                If (port.IOType.Equals(UBW32_interlocking.IOTYPE_OUTPUT)) Then
                    actualCommand.Register.G -= portSummary
                Else
                    actualCommand.Register.G += portSummary
                End If
            End If
        ElseIf (actualCommand.IOType = UBW32_interlocking.IOTYPE_CONFIGURATION) Then
            If (port.register.A = Register_A) Then
                actualCommand.Register.A += portSummary
            ElseIf (port.register.B = Register_B) Then
                actualCommand.Register.B += portSummary
            ElseIf (port.register.C = Register_C) Then
                actualCommand.Register.C += portSummary
            ElseIf (port.register.D = Register_D) Then
                actualCommand.Register.D += portSummary
            ElseIf (port.register.E = Register_E) Then
                actualCommand.Register.E += portSummary
            ElseIf (port.register.F = Register_F) Then
                actualCommand.Register.F += portSummary
            ElseIf (port.register.G = Register_G) Then
                actualCommand.Register.G += portSummary
            End If
        End If

    End Sub

    Private Function getOutputCommandAsString() As String
        Return getOutputCommandAsString(actualCommand)
    End Function

    Private Function getOutputCommandAsString(ByRef command As Command) As String
        Dim commandArray As New ArrayList()
        Dim commandString As String = ""

        commandArray.Add(command.IOType)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.A)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.B)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.C)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.D)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.E)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.F)
        commandArray.Add(COMMAND_SEPERATOR)
        commandArray.Add(command.Register.G)

        For Each obj As String In commandArray
            commandString += obj
        Next obj

        'ToDo integrate command.postchar
        commandString &= vbLf

        Console.WriteLine(commandString)

        Return commandString
    End Function

    Public Sub sendCommand()
        sendCommand(actualCommand)
    End Sub

    Public Sub sendCommand(ByRef command As Command)
        actualCommand = command
        Dim myCommand As String = getOutputCommandAsString(command)
        sendCommand(myCommand)
    End Sub

    Public Sub sendCommand(ByVal command As String)
        Dim returnPacket As String = ""
        Dim sendPacket As String = command

        communicationControl.serialPortInterlocking.WriteLine(command)

        Form_InterlockingControl.textBox_ConsoleInput.AppendText("-" + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleOutput.AppendText(sendPacket & vbCrLf)

        ' Read the command back
        returnPacket = communicationControl.serialPortInterlocking.ReadLine()

        Form_InterlockingControl.textBox_ConsoleInput.AppendText(returnPacket + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleOutput.AppendText("-" + vbCrLf)

        ' Read the OK back
        returnPacket = communicationControl.serialPortInterlocking.ReadLine()

        Form_InterlockingControl.textBox_ConsoleInput.AppendText(returnPacket + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleOutput.AppendText("-" + vbCrLf)

        ' Read the CR/LF back
        returnPacket = communicationControl.serialPortInterlocking.ReadLine()

        Form_InterlockingControl.textBox_ConsoleInput.AppendText(returnPacket + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleOutput.AppendText("-" + vbCrLf)

    End Sub

    Public Sub ReceivedInterlockingData()
        Form_InterlockingControl.textBox_ConsoleInput.AppendText("##############################################" + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleInput.AppendText("INPUT" + vbCrLf)
        Form_InterlockingControl.textBox_ConsoleInput.AppendText("##############################################" + vbCrLf)
    End Sub


End Class
