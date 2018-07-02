import win32com.client

excel = win32com.client.Dispatch("Excel.Applicarion")
excel.Visible = True
wb = excel.Workbooks.Open('C:\\Users\\최우진\\Dropbox\\원격물검침_국내논문\\남양주데이터')
ws = wb.ActiveSheet
cnt = 1
i = 1
while i < 101:
      for j in range(5):
            print(ws.Cells(cnt,j).Value)
      cnt = cnt + 1
      if ws.Cells(cnt,1).Value != i:
            i = i + 1
excel.Quit()
