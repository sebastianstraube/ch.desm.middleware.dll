<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form_MainControl
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
        Me.btn_commandCenter = New System.Windows.Forms.Button()
        Me.btn_trainControl = New System.Windows.Forms.Button()
        Me.btn_portTester = New System.Windows.Forms.Button()
        Me.btn_portStatus = New System.Windows.Forms.Button()
        Me.btn_InterlockingControl = New System.Windows.Forms.Button()
        Me.SuspendLayout()
        '
        'btn_commandCenter
        '
        Me.btn_commandCenter.Location = New System.Drawing.Point(12, 12)
        Me.btn_commandCenter.Name = "btn_commandCenter"
        Me.btn_commandCenter.Size = New System.Drawing.Size(141, 23)
        Me.btn_commandCenter.TabIndex = 0
        Me.btn_commandCenter.Text = "Command Center"
        Me.btn_commandCenter.UseVisualStyleBackColor = True
        '
        'btn_trainControl
        '
        Me.btn_trainControl.Location = New System.Drawing.Point(12, 57)
        Me.btn_trainControl.Name = "btn_trainControl"
        Me.btn_trainControl.Size = New System.Drawing.Size(141, 23)
        Me.btn_trainControl.TabIndex = 1
        Me.btn_trainControl.Text = "Train Control"
        Me.btn_trainControl.UseVisualStyleBackColor = True
        '
        'btn_portTester
        '
        Me.btn_portTester.Location = New System.Drawing.Point(12, 131)
        Me.btn_portTester.Name = "btn_portTester"
        Me.btn_portTester.Size = New System.Drawing.Size(141, 23)
        Me.btn_portTester.TabIndex = 2
        Me.btn_portTester.Text = "Port Tester"
        Me.btn_portTester.UseVisualStyleBackColor = True
        '
        'btn_portStatus
        '
        Me.btn_portStatus.Location = New System.Drawing.Point(12, 161)
        Me.btn_portStatus.Name = "btn_portStatus"
        Me.btn_portStatus.Size = New System.Drawing.Size(141, 23)
        Me.btn_portStatus.TabIndex = 3
        Me.btn_portStatus.Text = "Port Status"
        Me.btn_portStatus.UseVisualStyleBackColor = True
        '
        'btn_InterlockingControl
        '
        Me.btn_InterlockingControl.Location = New System.Drawing.Point(12, 87)
        Me.btn_InterlockingControl.Name = "btn_InterlockingControl"
        Me.btn_InterlockingControl.Size = New System.Drawing.Size(140, 23)
        Me.btn_InterlockingControl.TabIndex = 4
        Me.btn_InterlockingControl.Text = "Interlocking Control"
        Me.btn_InterlockingControl.UseVisualStyleBackColor = True
        '
        'Form_MainControl
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(164, 194)
        Me.Controls.Add(Me.btn_InterlockingControl)
        Me.Controls.Add(Me.btn_portStatus)
        Me.Controls.Add(Me.btn_portTester)
        Me.Controls.Add(Me.btn_trainControl)
        Me.Controls.Add(Me.btn_commandCenter)
        Me.Name = "Form_MainControl"
        Me.Text = "Dispatcher"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents btn_commandCenter As System.Windows.Forms.Button
    Friend WithEvents btn_trainControl As System.Windows.Forms.Button
    Friend WithEvents btn_portTester As System.Windows.Forms.Button
    Friend WithEvents btn_portStatus As System.Windows.Forms.Button
    Friend WithEvents btn_InterlockingControl As System.Windows.Forms.Button
End Class
