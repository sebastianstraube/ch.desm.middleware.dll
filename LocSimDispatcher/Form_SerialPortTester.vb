Public Class Form_SerialPortTester

    Dim SendBuffer(-1) As Byte

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Dim sendBuffer As Byte()
        Dim offset As Integer = 0
        Dim count As Integer = TextBox4.Text.Length

        ReDim sendBuffer(count)

        For i = 0 To count - 1
            sendBuffer(i) = Asc(Mid(TextBox4.Text, i + 1, 1))
        Next

        'Dim byte0, byte1, byte2 As Byte

        'If TextBox3.Text.Length = 4 And TextBox6.Text.Length = 2 Then
        'byte0 = Convert.ToInt32(Mid(TextBox3.Text, 1, 2), 16)
        ' byte1 = Convert.ToInt32(Mid(TextBox3.Text, 3, 2), 16)
        ' byte2 = Convert.ToInt32(Mid(TextBox6.Text, 1, 2), 16)
        ' TextBox7.Text = byte0 & "," & byte1 & "," & byte2
        'SendToSimulator(byte0, byte1, byte2)


        If Form_CommandCenter.re44.SerialController.SerialPortTester.IsOpen Then

            Form_CommandCenter.re44.SerialController.SerialPortTester.Write(sendBuffer, offset, count)

        Else
            MsgBox("SerialPortTester is not open!")
        End If
    End Sub



    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Dim sendBuffer As Byte()
        Dim offset As Integer = 0
        Dim count As Integer = -1


        ReDim sendBuffer(count)

        Dim TextString As String = TextBox2.Text

        Dim pos As Integer
        Dim value As Integer


        Do Until TextString = ""
            pos = InStr(TextString, ",")

            If pos <> 0 Then
                value = Int(Mid(TextString, 1, pos - 1))
                TextString = Mid(TextString, pos + 1)
            Else
                value = Int(TextString)
                TextString = ""
            End If

            ReDim Preserve sendBuffer(sendBuffer.Length)
            sendBuffer(sendBuffer.Length - 1) = value
        Loop



        'Dim byte0, byte1, byte2 As Byte

        'If TextBox3.Text.Length = 4 And TextBox6.Text.Length = 2 Then
        'byte0 = Convert.ToInt32(Mid(TextBox3.Text, 1, 2), 16)
        ' byte1 = Convert.ToInt32(Mid(TextBox3.Text, 3, 2), 16)
        ' byte2 = Convert.ToInt32(Mid(TextBox6.Text, 1, 2), 16)
        ' TextBox7.Text = byte0 & "," & byte1 & "," & byte2
        'SendToSimulator(byte0, byte1, byte2)


        If Form_CommandCenter.re44.SerialController.SerialPortTester.IsOpen Then

            Form_CommandCenter.re44.SerialController.SerialPortTester.Write(sendBuffer, offset, sendBuffer.Length)

        Else
            MsgBox("SerialPortTester is not open!")
        End If
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        If Form_CommandCenter.re44.SerialController.SerialPortTester.IsOpen Then
            Form_CommandCenter.re44.SerialController.SerialPortTester.WriteLine(TextBox4.Text)
        Else
            MsgBox("SerialPortTester is not open!")
        End If
    End Sub

End Class