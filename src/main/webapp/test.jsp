<HTML>
<HEAD>
    <TITLE> Add/Remove dynamic rows IN HTML table </TITLE>
    <SCRIPT language="javascript">
        FUNCTION
        addRow(tableID)
        {

            VAR
            table = document.getElementById(tableID);

            VAR
            rowCount = table.rows.length;
            VAR
            row = table.insertRow(rowCount);

            VAR
            cell1 = row.insertCell(0);
            VAR
            element1 = document.createElement("input");
            element1.type = "checkbox";
            cell1.appendChild(element1);

            VAR
            cell2 = row.insertCell(1);
            cell2.innerHTML = rowCount + 1;

            VAR
            cell3 = row.insertCell(2);
            VAR
            element2 = document.createElement("input");
            element2.type = "text";
            cell3.appendChild(element2);

        }

        FUNCTION
        deleteRow(tableID)
        {
            TRY
            {
                VAR
                table = document.getElementById(tableID);
                VAR
                rowCount = table.rows.length;

                FOR(VAR
                i = 0;
                i < rowCount;
                i++
            )
                {
                    VAR
                    row = table.rows[i];
                    VAR
                    chkbox = row.cells[0].childNodes[0];
                    IF(NULL != chkbox && TRUE == chkbox.checked)
                    {
                        table.deleteRow(i);
                        rowCount--;
                        i--;
                    }

                }
            }
            CATCH(e)
            {
                ALERT(e);
            }
        }

    </SCRIPT>
</HEAD>
<BODY>

<INPUT type="button" value="Add Row" onclick="addRow('dataTable')"/>

<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')"/>

<TABLE id="dataTable" width="350px" border="1">
    <TR>
        <TD><INPUT type="checkbox" NAME="chk"/></TD>
        <TD> 1</TD>
        <TD><INPUT type="text"/></TD>
    </TR>
</TABLE>

</BODY>
</HTML>