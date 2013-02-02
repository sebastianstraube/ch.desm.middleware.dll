<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form_InterlockingControl
    Inherits System.Windows.Forms.Form

    'Das Formular überschreibt den Löschvorgang, um die Komponentenliste zu bereinigen.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Wird vom Windows Form-Designer benötigt.
    Private components As System.ComponentModel.IContainer

    'Hinweis: Die folgende Prozedur ist für den Windows Form-Designer erforderlich.
    'Das Bearbeiten ist mit dem Windows Form-Designer möglich.  
    'Das Bearbeiten mit dem Code-Editor ist nicht möglich.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.ListBox_ComPorts = New System.Windows.Forms.ListBox()
        Me.btn_assignControllerToPort = New System.Windows.Forms.Button()
        Me.textBox_ConsoleOutput = New System.Windows.Forms.TextBox()
        Me.textBox_ConsoleInput = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.btn_SendScenario1 = New System.Windows.Forms.Button()
        Me.btn_SendSceanrio2 = New System.Windows.Forms.Button()
        Me.btn_SendScenario3 = New System.Windows.Forms.Button()
        Me.btn_SendScenario4 = New System.Windows.Forms.Button()
        Me.btn_SendScenario5 = New System.Windows.Forms.Button()
        Me.btn_SndScenario6 = New System.Windows.Forms.Button()
        Me.btn_SendSceanrio7 = New System.Windows.Forms.Button()
        Me.btn_SendScenario8 = New System.Windows.Forms.Button()
        Me.btn_SendScenario9 = New System.Windows.Forms.Button()
        Me.btn_SendScenario10 = New System.Windows.Forms.Button()
        Me.btn_FSSSperrmagnet = New System.Windows.Forms.Button()
        Me.btn_FSSKuppelstrommagnet = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'ListBox_ComPorts
        '
        Me.ListBox_ComPorts.FormattingEnabled = True
        Me.ListBox_ComPorts.Location = New System.Drawing.Point(12, 22)
        Me.ListBox_ComPorts.Name = "ListBox_ComPorts"
        Me.ListBox_ComPorts.Size = New System.Drawing.Size(64, 95)
        Me.ListBox_ComPorts.TabIndex = 0
        '
        'btn_assignControllerToPort
        '
        Me.btn_assignControllerToPort.Location = New System.Drawing.Point(12, 123)
        Me.btn_assignControllerToPort.Name = "btn_assignControllerToPort"
        Me.btn_assignControllerToPort.Size = New System.Drawing.Size(64, 23)
        Me.btn_assignControllerToPort.TabIndex = 1
        Me.btn_assignControllerToPort.Text = "Assign"
        Me.btn_assignControllerToPort.UseVisualStyleBackColor = True
        '
        'textBox_ConsoleOutput
        '
        Me.textBox_ConsoleOutput.Location = New System.Drawing.Point(82, 22)
        Me.textBox_ConsoleOutput.Multiline = True
        Me.textBox_ConsoleOutput.Name = "textBox_ConsoleOutput"
        Me.textBox_ConsoleOutput.Size = New System.Drawing.Size(321, 124)
        Me.textBox_ConsoleOutput.TabIndex = 2
        '
        'textBox_ConsoleInput
        '
        Me.textBox_ConsoleInput.Location = New System.Drawing.Point(409, 22)
        Me.textBox_ConsoleInput.Multiline = True
        Me.textBox_ConsoleInput.Name = "textBox_ConsoleInput"
        Me.textBox_ConsoleInput.Size = New System.Drawing.Size(334, 124)
        Me.textBox_ConsoleInput.TabIndex = 3
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(12, 6)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(28, 13)
        Me.Label1.TabIndex = 4
        Me.Label1.Text = "Com"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(79, 6)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(39, 13)
        Me.Label2.TabIndex = 5
        Me.Label2.Text = "Output"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(406, 6)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(31, 13)
        Me.Label3.TabIndex = 6
        Me.Label3.Text = "Input"
        '
        'btn_SendScenario1
        '
        Me.btn_SendScenario1.Location = New System.Drawing.Point(13, 176)
        Me.btn_SendScenario1.Name = "btn_SendScenario1"
        Me.btn_SendScenario1.Size = New System.Drawing.Size(81, 28)
        Me.btn_SendScenario1.TabIndex = 7
        Me.btn_SendScenario1.Text = "Grundstellung"
        Me.btn_SendScenario1.UseVisualStyleBackColor = True
        '
        'btn_SendSceanrio2
        '
        Me.btn_SendSceanrio2.Location = New System.Drawing.Point(100, 176)
        Me.btn_SendSceanrio2.Name = "btn_SendSceanrio2"
        Me.btn_SendSceanrio2.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendSceanrio2.TabIndex = 8
        Me.btn_SendSceanrio2.Text = "Signal gelb"
        Me.btn_SendSceanrio2.UseVisualStyleBackColor = True
        '
        'btn_SendScenario3
        '
        Me.btn_SendScenario3.Location = New System.Drawing.Point(173, 176)
        Me.btn_SendScenario3.Name = "btn_SendScenario3"
        Me.btn_SendScenario3.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario3.TabIndex = 9
        Me.btn_SendScenario3.Text = "Brücke"
        Me.btn_SendScenario3.UseVisualStyleBackColor = True
        '
        'btn_SendScenario4
        '
        Me.btn_SendScenario4.Location = New System.Drawing.Point(246, 176)
        Me.btn_SendScenario4.Name = "btn_SendScenario4"
        Me.btn_SendScenario4.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario4.TabIndex = 10
        Me.btn_SendScenario4.Text = "E Fahrt"
        Me.btn_SendScenario4.UseVisualStyleBackColor = True
        '
        'btn_SendScenario5
        '
        Me.btn_SendScenario5.Location = New System.Drawing.Point(319, 176)
        Me.btn_SendScenario5.Name = "btn_SendScenario5"
        Me.btn_SendScenario5.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario5.TabIndex = 11
        Me.btn_SendScenario5.Text = "Isol ES"
        Me.btn_SendScenario5.UseVisualStyleBackColor = True
        '
        'btn_SndScenario6
        '
        Me.btn_SndScenario6.Location = New System.Drawing.Point(392, 176)
        Me.btn_SndScenario6.Name = "btn_SndScenario6"
        Me.btn_SndScenario6.Size = New System.Drawing.Size(67, 28)
        Me.btn_SndScenario6.TabIndex = 12
        Me.btn_SndScenario6.Text = "Isol W1"
        Me.btn_SndScenario6.UseVisualStyleBackColor = True
        '
        'btn_SendSceanrio7
        '
        Me.btn_SendSceanrio7.Location = New System.Drawing.Point(465, 176)
        Me.btn_SendSceanrio7.Name = "btn_SendSceanrio7"
        Me.btn_SendSceanrio7.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendSceanrio7.TabIndex = 13
        Me.btn_SendSceanrio7.Text = "Isol 4"
        Me.btn_SendSceanrio7.UseVisualStyleBackColor = True
        '
        'btn_SendScenario8
        '
        Me.btn_SendScenario8.Location = New System.Drawing.Point(538, 176)
        Me.btn_SendScenario8.Name = "btn_SendScenario8"
        Me.btn_SendScenario8.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario8.TabIndex = 14
        Me.btn_SendScenario8.Text = "Strecke 1"
        Me.btn_SendScenario8.UseVisualStyleBackColor = True
        '
        'btn_SendScenario9
        '
        Me.btn_SendScenario9.Location = New System.Drawing.Point(611, 176)
        Me.btn_SendScenario9.Name = "btn_SendScenario9"
        Me.btn_SendScenario9.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario9.TabIndex = 15
        Me.btn_SendScenario9.Text = "Strecke 2"
        Me.btn_SendScenario9.UseVisualStyleBackColor = True
        '
        'btn_SendScenario10
        '
        Me.btn_SendScenario10.Location = New System.Drawing.Point(684, 176)
        Me.btn_SendScenario10.Name = "btn_SendScenario10"
        Me.btn_SendScenario10.Size = New System.Drawing.Size(67, 28)
        Me.btn_SendScenario10.TabIndex = 16
        Me.btn_SendScenario10.Text = "FS weg"
        Me.btn_SendScenario10.UseVisualStyleBackColor = True
        '
        'btn_FSSSperrmagnet
        '
        Me.btn_FSSSperrmagnet.Location = New System.Drawing.Point(12, 221)
        Me.btn_FSSSperrmagnet.Name = "btn_FSSSperrmagnet"
        Me.btn_FSSSperrmagnet.Size = New System.Drawing.Size(141, 28)
        Me.btn_FSSSperrmagnet.TabIndex = 17
        Me.btn_FSSSperrmagnet.Text = "FSS Sperrmagnet"
        Me.btn_FSSSperrmagnet.UseVisualStyleBackColor = True
        '
        'btn_FSSKuppelstrommagnet
        '
        Me.btn_FSSKuppelstrommagnet.Location = New System.Drawing.Point(159, 221)
        Me.btn_FSSKuppelstrommagnet.Name = "btn_FSSKuppelstrommagnet"
        Me.btn_FSSKuppelstrommagnet.Size = New System.Drawing.Size(145, 28)
        Me.btn_FSSKuppelstrommagnet.TabIndex = 18
        Me.btn_FSSKuppelstrommagnet.Text = "FSS Kuppelstrommagnet"
        Me.btn_FSSKuppelstrommagnet.UseVisualStyleBackColor = True
        '
        'Form_InterlockingControl
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(762, 267)
        Me.Controls.Add(Me.btn_FSSKuppelstrommagnet)
        Me.Controls.Add(Me.btn_FSSSperrmagnet)
        Me.Controls.Add(Me.btn_SendScenario10)
        Me.Controls.Add(Me.btn_SendScenario9)
        Me.Controls.Add(Me.btn_SendScenario8)
        Me.Controls.Add(Me.btn_SendSceanrio7)
        Me.Controls.Add(Me.btn_SndScenario6)
        Me.Controls.Add(Me.btn_SendScenario5)
        Me.Controls.Add(Me.btn_SendScenario4)
        Me.Controls.Add(Me.btn_SendScenario3)
        Me.Controls.Add(Me.btn_SendSceanrio2)
        Me.Controls.Add(Me.btn_SendScenario1)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.textBox_ConsoleInput)
        Me.Controls.Add(Me.textBox_ConsoleOutput)
        Me.Controls.Add(Me.btn_assignControllerToPort)
        Me.Controls.Add(Me.ListBox_ComPorts)
        Me.Name = "Form_InterlockingControl"
        Me.Text = "Form_InterlockingCommandCenter"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents ListBox_ComPorts As System.Windows.Forms.ListBox
    Friend WithEvents btn_assignControllerToPort As System.Windows.Forms.Button
    Friend WithEvents textBox_ConsoleOutput As System.Windows.Forms.TextBox
    Friend WithEvents textBox_ConsoleInput As System.Windows.Forms.TextBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents btn_SendScenario1 As System.Windows.Forms.Button
    Friend WithEvents btn_SendSceanrio2 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario3 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario4 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario5 As System.Windows.Forms.Button
    Friend WithEvents btn_SndScenario6 As System.Windows.Forms.Button
    Friend WithEvents btn_SendSceanrio7 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario8 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario9 As System.Windows.Forms.Button
    Friend WithEvents btn_SendScenario10 As System.Windows.Forms.Button
    Friend WithEvents btn_FSSSperrmagnet As System.Windows.Forms.Button
    Friend WithEvents btn_FSSKuppelstrommagnet As System.Windows.Forms.Button
End Class
