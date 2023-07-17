            /*<![CDATA[*/
            var stocks = /*[[${stocks}]*/
            /*]]>*/

            $('#txtSearch').on('keyup',function(){
                var value = $(this).val();
                console.log('Value = ' + value);

                var data = FilterFunction(value, stocks)

                rebuildTable(data)

            });

            function FilterFunction(value, data){
                var filteredData = []
                for(var i = 0; i<data.length; i++){
                    value = value.toLowerCase();
                    var name = data[i].name.toLowerCase();

                    if(name.includes(value)){
                        filteredData.push(data[i]);
                    }
                }
                return filteredData;
            }

            function rebuildTable(data){
                var table = document.getElementByID('stock')
                for(var i = 0; i< data.length; i++){
                    var row = <tr>
                                    <td>.........
                              </tr>
                              table.innerHTML += row
                }
            }

