function objectEval(text) {
	text = text.replace(/\n/g, ' ');
	text = text.replace(/\r/g, ' ');
	if (text.match(/^\s*\{.*\}\s*$/))
	{
		text = '[' + text + '][0]';
	}
	return eval(text);
}
