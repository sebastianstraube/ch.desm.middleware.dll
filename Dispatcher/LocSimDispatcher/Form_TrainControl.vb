Public Class Form_TrainControl

    Public OnColor As Color = Color.Gold
    Public OffColor As Color = Color.Silver
    Public FahrschalterStellung As Integer = 4
    Public FahrtrichtungsSchalterStelleung As Integer = 2
    Public PfeifenKnopfstellung As Integer = 1
    Public AussenLicht As Byte = 0
    Public InnenLicht As Byte = 0


    Private Sub SetGUIFahrschalterStellung()
        Select Case FahrschalterStellung
            Case 1
                RadioButton7.Checked = True
            Case 2
                RadioButton6.Checked = True
            Case 3
                RadioButton5.Checked = True
            Case 4
                RadioButton9.Checked = True
            Case 5
                RadioButton8.Checked = True
            Case 6
                RadioButton4.Checked = True
            Case 7
                RadioButton3.Checked = True
            Case 8
                RadioButton2.Checked = True
            Case 9
                RadioButton1.Checked = True
        End Select
    End Sub



    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        If Button1.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H31, &H38, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H31, &H38, 1)
            End If

            Button1.BackColor = OffColor
            Button1.Tag = 1

        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H31, &H38, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H31, &H38, 2)
            End If

            Button1.BackColor = OnColor
            Button1.Tag = 2

        End If

    End Sub

    Private Sub Button20_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button20.Click
        If Button20.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H27, &HF, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H27, &HF, 1)
            End If
            Button20.BackColor = OffColor
            Button20.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H27, &HF, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H27, &HF, 2)
            End If
            Button20.BackColor = OnColor
            Button20.Tag = 2
        End If
    End Sub

    Private Sub Button8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button8.Click
        If FahrschalterStellung < 9 Then
            FahrschalterStellung = FahrschalterStellung + 1
            SetGUIFahrschalterStellung()
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
            End If

        End If
    End Sub

    Private Sub Button9_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button9.Click
        If FahrschalterStellung > 1 Then
            FahrschalterStellung = FahrschalterStellung - 1
            SetGUIFahrschalterStellung()
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
            End If
        End If

    End Sub



    Private Sub RadioButton1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton1.Click
        FahrschalterStellung = 9
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If


    End Sub

    Private Sub RadioButton2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton2.Click
        FahrschalterStellung = 8
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub


    Private Sub RadioButton3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton3.Click
        FahrschalterStellung = 7
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton4.Click
        FahrschalterStellung = 6
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton8.Click
        FahrschalterStellung = 5
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If
        Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
    End Sub

    Private Sub RadioButton9_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton9.Click
        FahrschalterStellung = 4
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton5.Click
        FahrschalterStellung = 3
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton6.Click
        FahrschalterStellung = 2
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton7.Click
        FahrschalterStellung = 1
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H3A, &H98, FahrschalterStellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H3A, &H98, FahrschalterStellung)
        End If

    End Sub

    Private Sub RadioButton16_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton16.Click
        FahrtrichtungsSchalterStelleung = 1
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H36, &HB0, FahrtrichtungsSchalterStelleung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H36, &HB0, FahrtrichtungsSchalterStelleung)
        End If

    End Sub

    Private Sub RadioButton17_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton17.Click
        FahrtrichtungsSchalterStelleung = 2
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H36, &HB0, FahrtrichtungsSchalterStelleung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H36, &HB0, FahrtrichtungsSchalterStelleung)
        End If

    End Sub

    Private Sub RadioButton18_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton18.Click
        FahrtrichtungsSchalterStelleung = 3
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H36, &HB0, FahrtrichtungsSchalterStelleung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H36, &HB0, FahrtrichtungsSchalterStelleung)
        End If

    End Sub

    Private Sub TrackBar7_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar7.Scroll
        Label15.Text = Math.Round(10 * TrackBar7.Value / 255, 1)
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H27, &H75, TrackBar7.Value, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H27, &H75, TrackBar7.Value)
        End If

    End Sub

    Private Sub TrackBar6_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar6.Scroll
        Label16.Text = Math.Round(10 * TrackBar6.Value / 255, 1)
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H4E, &H85, TrackBar6.Value, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H4E, &H85, TrackBar6.Value)
        End If

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        If Button3.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H34, &H58, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H34, &H58, 1)
            End If

            Button3.BackColor = OffColor
            Button3.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H34, &H58, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H34, &H58, 2)
            End If
            Button3.BackColor = OnColor
            Button3.Tag = 2
        End If
    End Sub





    Private Sub Button18_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button18.Click
        If Button18.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &HE0, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &HE0, 1)
            End If

            Button18.BackColor = OffColor
            Button18.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &HE0, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &HE0, 2)
            End If

            Button18.BackColor = OnColor
            Button18.Tag = 2
        End If
    End Sub

    Private Sub Button16_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button16.Click
        '"SchalterTürfreigabe-Links"

        If Button16.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H90, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H90, 1)
            End If

            Button16.BackColor = OffColor
            Button16.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H90, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H90, 2)
            End If

            Button16.BackColor = OnColor
            Button16.Tag = 2
        End If
    End Sub

    Private Sub Button14_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button14.Click
        '"SchalterTürfreigabe-Rechts"

        If Button14.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H86, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H86, 1)
            End If

            Button14.BackColor = OffColor
            Button14.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H86, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H86, 2)
            End If

            Button14.BackColor = OnColor
            Button14.Tag = 2
        End If
    End Sub

    Private Sub Button15_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button15.Click
        '"SchalterTürveriegelung"
        If Button15.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H18, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H18, 1)
            End If

            Button15.BackColor = OffColor
            Button15.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H47, &H18, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H47, &H18, 2)
            End If

            Button15.BackColor = OnColor
            Button15.Tag = 2
        End If
    End Sub

    Private Sub Button10_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button10.Click
        '"SchleuderbremseTaste"
        If Button10.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H6D, &HC4, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H6D, &HC4, 1)
            End If


            Button10.BackColor = OffColor
            Button10.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H6D, &HC4, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H6D, &HC4, 2)
            End If


            Button10.BackColor = OnColor
            Button10.Tag = 2
        End If
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        '"SchalterStromabnehmer"
        If Button2.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H32, &H64, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H32, &H64, 1)
            End If


            Button2.BackColor = OffColor
            Button2.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H32, &H64, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H32, &H64, 2)
            End If


            Button2.BackColor = OnColor
            Button2.Tag = 2
        End If
    End Sub

    Private Sub Button19_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button19.Click
        '"Totmannpedal"
        If Button19.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then

                Form_CommandCenter.re44.SendToCabine(&H7E, &H90, 0, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H7E, &H90, 0)
            End If


            Button19.BackColor = OffColor
            Button19.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H7E, &H90, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H7E, &H90, 1)
            End If


            Button19.BackColor = OnColor
            Button19.Tag = 2
        End If
    End Sub

    Private Sub Button26_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button26.Click
        '"EPVentil243"
        If Button26.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H5E, &HEC, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H5E, &HEC, 1)
            End If


            Button26.BackColor = OffColor
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H5E, &HEC, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H5E, &HEC, 2)
            End If
            Button26.BackColor = OnColor
        End If
    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button5.Click
        '"SchalterZugsbeleuchtung"

        If Button5.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H79, &H7C, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H79, &H7C, 1)
            End If


            Button5.BackColor = OffColor

            Button5.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H79, &H7C, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H79, &H7C, 2)
            End If

            Button5.BackColor = OnColor
            Button5.Tag = 2
        End If
    End Sub


    Private Sub Button7_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button7.Click
        If Button7.BackColor = OnColor Then
            ' Turn off
            Button7.BackColor = OffColor
            Button7.Tag = 1
            SetFronLightTop()
            SetFronLightRight()
            SetFronLightLeft()
        Else
            ' Turn on
            Button7.BackColor = OnColor
            Button7.Tag = 2
            SetFronLightTop()
            SetFronLightRight()
            SetFronLightLeft()
        End If
    End Sub


    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button6.Click
        '"SchalterZugsammelschiene"
        If Button6.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H42, &H4, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H42, &H4, 1)
            End If

            Button6.BackColor = OffColor
            Button6.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H42, &H4, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H42, &H4, 2)
            End If

            Button6.BackColor = OnColor
            Button6.Tag = 2
        End If
    End Sub

    Private Sub RadioButton10_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton10.Click
        '"DruckknopfSignalPfeife"
        PfeifenKnopfstellung = 1

        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H49, &HD4, PfeifenKnopfstellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H49, &HD4, PfeifenKnopfstellung)
        End If


    End Sub

    Private Sub RadioButton11_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton11.Click
        PfeifenKnopfstellung = 2
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H49, &HD4, PfeifenKnopfstellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H49, &HD4, PfeifenKnopfstellung)
        End If


    End Sub

    Private Sub RadioButton12_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton12.Click
        PfeifenKnopfstellung = 3
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H49, &HD4, PfeifenKnopfstellung, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H49, &HD4, PfeifenKnopfstellung)
        End If


    End Sub


    Private Sub Button25_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button25.Click
        ' Case "RückstelletasteZugsicherung"
        If Button25.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H5E, &H88, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H5E, &H88, 1)
            End If

            Button25.BackColor = OffColor
            Button25.Tag = 1
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H5E, &H88, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H5E, &H88, 2)
            End If

            Button25.BackColor = OnColor
            Button25.Tag = 2
        End If
    End Sub






    Private Sub Button12_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button12.Click
        TrackBar6.Value = 0
        Label16.Text = Math.Round(10 * TrackBar6.Value / 255, 1)
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&HE4, &H85, TrackBar6.Value, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&HE4, &H85, TrackBar6.Value)
        End If
    End Sub



    Private Sub Button11_Click_1(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button11.Click
        TrackBar7.Value = 127
        Label15.Text = Math.Round(10 * TrackBar7.Value / 255, 1)
        If Form_CommandCenter.CabineSimulator.Checked Then
            Form_CommandCenter.re44.SendToCabine(&H27, &H75, TrackBar7.Value, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H27, &H75, TrackBar7.Value)
        End If
    End Sub





    Private Sub Button13_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button13.Click
        TrackBar8.Value = 127
        Form_CommandCenter.re44.SendToSimulator(&H27, &H74, TrackBar7.Value)
    End Sub

    Private Sub TrackBar8_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar8.Scroll
        Form_CommandCenter.re44.SendToSimulator(&H27, &H74, TrackBar7.Value)
    End Sub

    Private Sub Button17_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button17.Click
        TrackBar9.Value = 0
        Form_CommandCenter.re44.SendToSimulator(&H4E, &H84, TrackBar7.Value)
    End Sub


    Private Sub TrackBar9_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar9.Scroll
        Form_CommandCenter.re44.SendToSimulator(&H4E, &H84, TrackBar7.Value)
    End Sub


    Private Sub Button28_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button28.Click
        '"EPVentil243"
        If Button28.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.UpdateBoBoGUI(&H46, &HB4, 1)
            Else
                Form_CommandCenter.re44.SendToCabine(&H46, &HB4, 1, 0, 1)
                Form_CommandCenter.re44.SendToCabine(&H46, &HB4, 1, 1, 1)

                Form_CommandCenter.re44.UpdateBoBoGUI(&H46, &HB4, 1)
            End If

            Button28.BackColor = OffColor
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.UpdateBoBoGUI(&H46, &HB4, 2)
            Else
                Form_CommandCenter.re44.SendToCabine(&H46, &HB4, 2, 0, 1)
                Form_CommandCenter.re44.SendToCabine(&H46, &HB4, 2, 1, 1)
                Form_CommandCenter.re44.UpdateBoBoGUI(&H46, &HB4, 2)
            End If
            Button28.BackColor = OnColor
        End If
    End Sub

    Private Sub TrackBar10_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar10.Scroll
        SetFronLightTop()
    End Sub
    Private Sub TrackBar11_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar11.Scroll
        SetFronLightLeft()
    End Sub
    Private Sub TrackBar12_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TrackBar12.Scroll
        SetFronLightRight()
    End Sub



    Private Sub SetFronLightTop()
        If Button7.Tag = 1 Then
            ' dark
            Panel1.BackColor = Color.Black
            AussenLicht = (AussenLicht And 183)
        Else
            Select Case TrackBar10.Value
                Case 1
                    'Rot
                    Panel1.BackColor = Color.Red
                    AussenLicht = (AussenLicht Or 64)
                Case 2
                    'dark
                    Panel1.BackColor = Color.Black
                    AussenLicht = (AussenLicht And 183)
                Case 3
                    'white
                    Panel1.BackColor = Color.White
                    AussenLicht = (AussenLicht Or 8)
            End Select
        End If

        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Send new light status to dispatcher
            Form_CommandCenter.re44.SendToCabine(&H7B, &H70, AussenLicht, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H7B, &H70, AussenLicht)
        End If
    End Sub

    Private Sub SetFronLightLeft()
        If Button7.Tag = 1 Then
            ' dark
            Panel2.BackColor = Color.Black
            AussenLicht = (AussenLicht And 111)
        Else
            Select Case TrackBar11.Value
                Case 1
                    'Rot
                    Panel2.BackColor = Color.Red
                    AussenLicht = (AussenLicht Or 128)
                Case 2
                    'dark
                    Panel2.BackColor = Color.Black
                    AussenLicht = (AussenLicht And 111)
                Case 3
                    'white
                    Panel2.BackColor = Color.White
                    AussenLicht = (AussenLicht Or 16)
            End Select
        End If
        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Send new light status to dispatcher
            Form_CommandCenter.re44.SendToCabine(&H7B, &H70, AussenLicht, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H7B, &H70, AussenLicht)
        End If
    End Sub
    Private Sub SetFronLightRight()
        If Button7.Tag = 1 Then
            ' dark
            Panel3.BackColor = Color.Black
            AussenLicht = (AussenLicht And 219)
        Else
            Select Case TrackBar12.Value
                Case 1
                    'Rot
                    Panel3.BackColor = Color.Red
                    AussenLicht = (AussenLicht Or 32)
                Case 2
                    'dark
                    Panel3.BackColor = Color.Black
                    AussenLicht = (AussenLicht And 219)
                Case 3
                    'white
                    Panel3.BackColor = Color.White
                    AussenLicht = (AussenLicht Or 4)
            End Select
        End If
        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Send new light status to dispatcher
            Form_CommandCenter.re44.SendToCabine(&H7B, &H70, AussenLicht, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H7B, &H70, AussenLicht)
        End If
    End Sub


    Private Sub Button24_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button24.Click
        If Button24.BackColor = OnColor Then
            ' Turn off
            InnenLicht = InnenLicht And 127
            Button24.BackColor = OffColor
            Panel4.BackColor = Color.Black
        Else
            ' Turn on
            InnenLicht = InnenLicht Or 128
            Button24.BackColor = OnColor
            Panel4.BackColor = Color.White
        End If

        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Send new light status to dispatcher
            Form_CommandCenter.re44.SendToCabine(&H7E, &H90, InnenLicht, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H7E, &H90, InnenLicht)
        End If

    End Sub

    Private Sub Button21_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button21.Click
        If Button21.BackColor = OnColor Then
            ' Turn off
            InnenLicht = InnenLicht And 191
            Button21.BackColor = OffColor
            Panel17.BackColor = Color.Black
        Else
            ' Turn on
            InnenLicht = InnenLicht Or 64
            Button21.BackColor = OnColor
            Panel17.BackColor = Color.White
        End If

        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Send new light status to dispatcher
            Form_CommandCenter.re44.SendToCabine(&H7E, &H90, InnenLicht, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H7E, &H90, InnenLicht)
        End If
    End Sub



    Private Sub Button22_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button22.Click
        '"EPVentil243"
        If Button22.BackColor = OnColor Then
            ' Turn off
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H27, &H77, 1, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H27, &H77, 1)
            End If


            Button22.BackColor = OffColor
        Else
            ' Turn on
            If Form_CommandCenter.CabineSimulator.Checked Then
                Form_CommandCenter.re44.SendToCabine(&H27, &H77, 2, 0, 1)
            Else
                Form_CommandCenter.re44.SendToSimulator(&H27, &H77, 2)
            End If
            Button22.BackColor = OnColor
        End If
    End Sub

    Private Sub RadioButton15_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton15.Click
        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Kompressor on A
            Form_CommandCenter.re44.SendToCabine(&H27, &H76, 1, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H27, &H76, 1)
        End If
    End Sub

    Private Sub RadioButton13_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton13.Click
        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Kompressor on 0
            Form_CommandCenter.re44.SendToCabine(&H27, &H76, 2, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H27, &H76, 2)
        End If
    End Sub

    Private Sub RadioButton14_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles RadioButton14.Click
        If Form_CommandCenter.CabineSimulator.Checked Then
            ' Kompressor on D
            Form_CommandCenter.re44.SendToCabine(&H27, &H76, 3, 0, 1)
        Else
            Form_CommandCenter.re44.SendToSimulator(&H27, &H76, 3)
        End If
    End Sub

    Private Sub Button29_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button29.Click
        Form_CommandCenter.re44.SendToCabine(&H5D, &HC0, 0, 0, 1)
    End Sub
End Class