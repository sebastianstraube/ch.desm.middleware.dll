Public Class IOElement
    Inherits UBW32_interlocking

    Dim FSSSperrmagnet As UBW32_interlocking.IOPort
    Dim FSSKuppelstrommagnet As UBW32_interlocking.IOPort

    Private Sub defineFSSperrmagnet()
        FSSSperrmagnet.Description = "FSS Sperrmagnet"
        FSSSperrmagnet.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        FSSSperrmagnet.register.E = UBW32_interlocking.Register_E
    End Sub

    Public Sub sendFSSSperrmagnet(enabled As Boolean)
        defineFSSperrmagnet()
        setFSSSperrmagnetON(enabled)
        addIOPortToActualCommand(FSSSperrmagnet)
        sendCommand()
    End Sub

    Public Sub setFSSSperrmagnetON(enabled As Boolean)
        If (enabled) Then
            FSSSperrmagnet.pins.pin6 = UBW32_interlocking.PIN6_OUTPUT_ENABLE
        Else
            FSSSperrmagnet.pins.pin6 = UBW32_interlocking.PIN6_OUTPUT_DISABLE
        End If
    End Sub



    Private Sub defineFSSKuppelstrommagnet()
        FSSKuppelstrommagnet.Description = "FSS Kuppelstrommagnet"
        FSSKuppelstrommagnet.IOType = UBW32_interlocking.IOTYPE_OUTPUT
        FSSKuppelstrommagnet.register.E = UBW32_interlocking.Register_E
    End Sub

    Public Sub sendFSSKuppelstrommagnet(enabled As Boolean)
        defineFSSKuppelstrommagnet()
        setFSSKuppelstrommagnetON(enabled)
        addIOPortToActualCommand(FSSKuppelstrommagnet)
        sendCommand()
    End Sub

    Public Sub setFSSKuppelstrommagnetON(enabled As Boolean)
        If (enabled) Then
            FSSKuppelstrommagnet.pins.pin5 = UBW32_interlocking.PIN5_OUTPUT_ENABLE
        Else
            FSSKuppelstrommagnet.pins.pin5 = UBW32_interlocking.PIN5_OUTPUT_DISABLE
        End If
    End Sub
End Class
