package extra.itextpdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/8/2 20:27
 */
public class ITextPdf {

    private static final String ORIG = "/Users/kmj/OneDrive/书籍/算法图解.pdf";
    private static final String DEST = "/Users/kmj/Temp/算法图解.pdf";

    public static void main(String[] args) throws
            IOException {
        PdfDocument pdfDocument = new PdfDocument(
                new PdfReader(ORIG),
                new PdfWriter(DEST)
        );

        final PdfOutline root = pdfDocument.getOutlines(true);
        final List<PdfOutline> pdfOutlines = new ArrayList<>();
        getAllPdfOutline(root, pdfOutlines);
        pdfOutlines.forEach(pdfOutline -> System.out.println(pdfOutline.getTitle()));
        pdfDocument.close();
    }

    public static void getAllPdfOutline(PdfOutline root, List<PdfOutline> list) {
        list.add(root);
        final List<PdfOutline> allChildren = root.getAllChildren();
        if (!allChildren.isEmpty()) {
            for (PdfOutline allChild : allChildren) {
                getAllPdfOutline(allChild, list);
            }
        }
    }

}
