package practice;


import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


public class ViewPart1 extends ViewPart {

	public static Label ansLabel;

	public ViewPart1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout (2, true);
		parent.setLayout (gridLayout);
		

		Button button0 = new Button (parent, SWT.PUSH);
		button0.setText ("button0");
		GridData data = new GridData (SWT.FILL, SWT.FILL, false, false);
		button0.setLayoutData (data);

		Button button1 = new Button (parent, SWT.PUSH);
		button1.setText ("button1");
		data = new GridData (SWT.FILL, SWT.FILL, false, false);
		button1.setLayoutData (data);

		Group group = new Group (parent, SWT.NONE);
		data = new GridData (SWT.FILL, SWT.FILL, true, true, 3, 3);
		group.setLayoutData (data);
		group.setText("Answer");
		
		GridLayout groupLayout = new GridLayout(3, false);
	    group.setLayout(groupLayout);

	    Label queLabel = new Label(group, SWT.NONE);
	    queLabel.setText("Please enter a description of the desired code: ");

	    Text text = new Text(group, SWT.BORDER);
	    GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false);
	    text.setLayoutData(textData);
	    
	    Button submitButton = new Button(group, SWT.PUSH);
        submitButton.setText("Submit");
        
	    
        ansLabel = new Label(group, SWT.WRAP);
        GridData ansLabelData = new GridData(SWT.FILL, SWT.FILL, true, true);
        ansLabel.setLayoutData(ansLabelData);

        
        
        submitButton.addListener(SWT.Selection, e -> {
            String selectedText = text.getText(); // Submit 버튼 클릭 시 Text 위젯의 값을 가져옵니다.
            OpenAIHandler handler = new OpenAIHandler();

            try {
                String reponse = (String) handler.callOpenAPI(selectedText); 
                if (reponse != null) {
                    Display.getDefault().asyncExec(() -> {
                        if (ViewPart1.ansLabel != null && !ViewPart1.ansLabel.isDisposed()) {
                        	System.out.print("yes");
                            ViewPart1.ansLabel.setText(reponse);
                        }
                    });
                }
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });

//		parent.addDisposeListener(e -> {
//	        button0.dispose();
//	        button1.dispose();
//	        group.dispose();
//	    });

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
