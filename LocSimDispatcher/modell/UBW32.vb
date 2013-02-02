Imports System.IO.Ports

Public Class UBW32
    Inherits CommunicationController

    Public pinDefinition(-1) As UBW32_Pins

    Public PortString As String

    Public Register_A As Integer
    Public Register_B As Integer
    Public Register_C As Integer
    Public Register_D As Integer
    Public Register_E As Integer
    Public Register_F As Integer
    Public Register_G As Integer

    Public Const bit0 = 1
    Public Const bit1 = 2
    Public Const bit2 = 4
    Public Const bit3 = 8
    Public Const bit4 = 16
    Public Const bit5 = 32
    Public Const bit6 = 64
    Public Const bit7 = 128
    Public Const bit8 = 256
    Public Const bit9 = 512
    Public Const bit10 = 1024
    Public Const bit11 = 2048
    Public Const bit12 = 4096
    Public Const bit13 = 8192
    Public Const bit14 = 16384
    Public Const bit15 = 32768

    Public Const UBS32_Analog_AN0_Average As Integer = 5
    Public Const UBW32_1_AN0_Hysterese As Integer = 20
    Public Const UBS32_Analog_AN1_Average As Integer = 5
    Public Const UBW32_1_AN1_Hysterese As Integer = 20

    Public analogIN As String

    Public AN0_Buffer(UBW32.UBS32_Analog_AN0_Average) As Integer
    Public AN0_BufferIndex As Integer = 0
    Public AN0_Average As Integer
    Public AN0_Current As Integer

    Public AN1_Buffer(UBW32.UBS32_Analog_AN1_Average) As Integer
    Public AN1_BufferIndex As Integer = 0
    Public AN1_Average As Integer
    Public AN1_Current As Integer

    Private versionString As String

    Public Structure UBW32_Pins
        Public Register As String
        Public Port As Integer
        Public Type As String
        Public ConfigCommand As String
        Public AnalogPinMask As Integer
    End Structure

    Public Structure UBWdigitalInput
        Public Register As String
        Public OldValue As Integer
        Public NewValue As Integer
    End Structure

    Public Sub initController()
        Dim ReturnPacket As String
        Dim PortArray() As String

        If serialPortUBW32_Train.IsOpen Then
            serialPortUBW32_Train.Close()
        End If

        Try
            With serialPortUBW32_Train
                .PortName = Form_CommandCenter.ListBox1.SelectedItem()
                .BaudRate = 9600
                .Parity = Parity.None
                .DataBits = 8
                .StopBits = StopBits.One
            End With
            serialPortUBW32_Train.Open()

            Form_CommandCenter.Button10.Text = "Release"
            Form_CommandCenter.Label25.Text = serialPortUBW32_Train.PortName
            Form_CommandCenter.ListBox1.ClearSelected()



        Catch ex As Exception
            MsgBox(ex.ToString)
        End Try

        ' Get the UBW Version

        serialPortUBW32_Train.WriteLine("V" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Get the version
        versionString = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()

        'The "PD" command stands for "Pin Direction". It allows you to set the direction on just one pin at a time. (Input or Output)
        'Format: "PD,<Port>,<Pin>,<Direction><CR>" 
        '<Port>: This is the character A, B, C, D, E, F or G depending upon which port you want to change. 
        '<Pin>: This is a number between and including 0 to 31. It indicates which pin in the port you want to change the direction on. 
        '<Direction>: This is either "0" or "1", for Output (0) or Input (1).
        'Example: "PD,B,2,1"  - This would change Port B, pin 2 to an input.
        'Return Packet: "OK" 

        'The "CA" Command  : Configure Analog inputs
        'CA command configures and turns on the analog inputs - use before the IA command (see below).
        'Format: "CA,<PinBitmask><CR>" 
        '<PinBitmask> is required, represents the bits of the analog inputs that you want to 'turn on' to be analog inputs. Valid values are 0 to 65,535. Each bit corresponds to an ANx (analog input pin). See the UBW32 scheamatic to see what pins each analog input is on.
        'Example: "CA,11<CR>" would set AN0, AN1 and AN3 to be analog inputs. 
        'Return Packet: "OK"

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 1
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 2
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 3
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 4
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)


        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 5
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 6
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 3
        pinDefinition(pinDefinition.Length - 1).Register = "A"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Port = 4
        pinDefinition(pinDefinition.Length - 1).Register = "A"
        pinDefinition(pinDefinition.Length - 1).Type = "[Digital IN]"
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf
        serialPortUBW32_Train.WriteLine("PD," & pinDefinition(pinDefinition.Length - 1).Register & "," & pinDefinition(pinDefinition.Length - 1).Port & ",1" & vbLf)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        'Initialize Analog Output PWM

        ReDim Preserve pinDefinition(pinDefinition.Length)
        pinDefinition(pinDefinition.Length - 1).Type = "[Analog Output]"
        pinDefinition(pinDefinition.Length - 1).Register = "D"
        pinDefinition(pinDefinition.Length - 1).Port = 0

        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "PM," & pinDefinition(pinDefinition.Length - 1).Port + 1 & ",0" & vbLf
        serialPortUBW32_Train.WriteLine(pinDefinition(pinDefinition.Length - 1).ConfigCommand)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()

        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ' Define Analog INPUT pin(s)
        ReDim Preserve pinDefinition(pinDefinition.Length)

        pinDefinition(pinDefinition.Length - 1).Type = "[Analog Inputs]"
        pinDefinition(pinDefinition.Length - 1).AnalogPinMask = 3
        pinDefinition(pinDefinition.Length - 1).ConfigCommand = "CA," & pinDefinition(pinDefinition.Length - 1).AnalogPinMask & vbLf
        serialPortUBW32_Train.WriteLine(pinDefinition(pinDefinition.Length - 1).ConfigCommand)
        ' Read the command back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read the CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        Form_PortState.ListBox1.Items.Add("Pin: " & pinDefinition(pinDefinition.Length - 1).Register & pinDefinition(pinDefinition.Length - 1).Port & " - " & pinDefinition(pinDefinition.Length - 1).Type & " - Config Command: " & pinDefinition(pinDefinition.Length - 1).ConfigCommand)

        ' Initialize Analog Input Variable
        serialPortUBW32_Train.WriteLine("IA," & pinDefinition(pinDefinition.Length - 1).AnalogPinMask & ",10,1" & vbLf)
        'Read Command serialPort
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read IA ports
        analogIN = serialPortUBW32_Train.ReadLine()
        ' Read OK back
        ReturnPacket = serialPortUBW32_Train.ReadLine()
        ' Read CR/LF back
        ReturnPacket = serialPortUBW32_Train.ReadLine()

        PortArray = Split(analogIN, ",")
        AN0_Current = PortArray(1)
        AN1_Current = PortArray(2)


        For i As Integer = 0 To UBS32_Analog_AN0_Average
            AN0_Buffer(i) = AN0_Current
        Next

        AN0_Average = AN0_Current

        For i As Integer = 0 To UBS32_Analog_AN1_Average
            AN1_Buffer(i) = AN1_Current
        Next

        AN1_Average = AN1_Current

        Form_PortState.Label3.Text = AN0_Current & " (" & AN0_Average & ")"
        Form_PortState.Label4.Text = AN1_Current & " (" & AN1_Average & ")"


        Form_CommandCenter.ServeUBW32_train.Enabled = True
    End Sub

End Class
