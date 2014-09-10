

<!-- pager --> 
<div class="pager"> 
   <button type="button" class="first"></button>
   <button type="button" class="prev"></button>
   <span class="pagedisplay"></span> <!-- this can be any element, including an input --> 
   <button type="button" class="next"></button>
   <button type="button" class="last"></button>
   <select class="pagesize" title="Select page size"> 
      <option selected="selected" value="10">10</option> 
      <option value="20">20</option> 
      <option value="30">30</option> 
      <option value="40">40</option> 
   </select>
</div>
<script type="text/javascript">
$(document).ready(function(){	
		  
	$(".tablesorter").tablesorter().tablesorterPager({
      // target the pager markup - see the HTML block below
      container: $(".pager"),
      // output string - default is '{page}/{totalPages}'; possible variables: {page}, {totalPages}, {startRow}, {endRow} and {totalRows}
      output: '{page}/{totalPages}页  共{totalRows}条记录 '
    );
			
});
</script>	