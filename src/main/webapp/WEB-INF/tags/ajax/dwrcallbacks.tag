<%@ tag body-content="scriptless" %>

<%@ attribute name="functionCall" required="true" %>
<%@ attribute name="id" required="true" %>

<script type='text/javascript'>
	<![CDATA[
	
	var get${id}Callback = function(result) {
		if (result != null && typeof result == 'object') {
			alert(dwr.util.toDescriptiveString(result, 2))
		} else {
			dwr.util.setValue('target${id}', dwr.util.toDescriptiveString(result, 1))
		}
	}
	
	toggleStatus${id} = new Boolean(true)
	
	var toggleDetails${id} = function() {
		if (toggleStatus${id}) {
			${functionCall}
		} else {
			dwr.util.setValue('target${id}', '')
		}
		toggleStatus${id} = !toggleStatus${id}
	}
	
	]]>
</script>
