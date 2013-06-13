package de.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.ControlType;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.IEnumInspector;
import de.agito.cps.core.bpmo.api.enums.IBODataElement;
import de.agito.cps.core.bpmo.api.enums.IBOId;
import de.agito.cps.core.bpmo.api.enums.IBONode;

/**
 * Enum for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum Invoice implements IBODataElement {

	/**
	 * <b>Invoice Root</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType NODE_KEY_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	Invoice("Invoice", "Invoice$Invoice", ControlType.DEFAULT),

	/**
	 * <b>Invoicing party</b>
	 * <p>
	 * Name of party sending the invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	InvoicingParty("InvoicingParty", "Invoice$InvoicingParty", ControlType.DEFAULT),

	/**
	 * <b>Invoice number</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	InvoiceNumber("InvoiceNumber", "Invoice$InvoiceNumber", ControlType.DEFAULT),

	/**
	 * <b>Order number</b>
	 * <p>
	 * Order number corresponding to invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType STRING}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	OrderNumber("OrderNumber", "Invoice$OrderNumber", ControlType.DEFAULT),

	/**
	 * <b>Invoice date</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	InvoiceDate("InvoiceDate", "Invoice$InvoiceDate", ControlType.DEFAULT),

	/**
	 * <b>Invoice received</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType DATE}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	InvoiceReceived("InvoiceReceived", "Invoice$InvoiceReceived", ControlType.DEFAULT),

	/**
	 * <b>Term of payment (Days)</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TermOfPayment("TermOfPayment", "Invoice$TermOfPayment", ControlType.DEFAULT),

	/**
	 * <b>Position per tax rate</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType TABLE_DEFINITION}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPostions("TaxPostions", "Invoice$TaxPostions", ControlType.DEFAULT),

	/**
	 * <b>Net amount €</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPostions$NetAmount("NetAmount", "Invoice$TaxPostions$NetAmount", ControlType.DEFAULT),

	/**
	 * <b>Tax rate</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPostions$TaxRate("TaxRate", "Invoice$TaxPostions$TaxRate", ControlType.DEFAULT),

	/**
	 * <b>Tax amount</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPostions$TaxAmount("TaxAmount", "Invoice$TaxPostions$TaxAmount", ControlType.DEFAULT),

	/**
	 * <b>Tax amount</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType COLUMN_DEFINITION}<i><br>
	 * <i>{@link DataType NUMERIC}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	TaxPostions$TotalAmount("TotalAmount", "Invoice$TaxPostions$TotalAmount", ControlType.DEFAULT),

	/**
	 * <b>Invoice copy</b>
	 * <p>
	 * Digital copy of invoice as PDF file
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ATTACHMENT}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	InvoiceAttachment("InvoiceAttachment", "Invoice$InvoiceAttachment", ControlType.DEFAULT),

	/**
	 * <b>Profitcenter</b>
	 * <p>
	 * Profitcenter where is responsible for this invoice
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	OrderProfitcenter("OrderProfitcenter", "Invoice$OrderProfitcenter", ControlType.DEFAULT),

	/**
	 * <b>Cost center</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType ENUM}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	OrderCostCenter("OrderCostCenter", "Invoice$OrderCostCenter", ControlType.DEFAULT),

	/**
	 * <b>Order checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	OrderChecked("OrderChecked", "Invoice$OrderChecked", ControlType.DEFAULT),

	/**
	 * <b>Shipment checked?</b>
	 * <p>
	 * <i>{@link DefinitionArtifactType CHARACTERISTIC_DEFINITION}<i><br>
	 * <i>{@link DataType BOOLEAN}<i><br>
	 * <i>{@link ControlType DEFAULT}<i><br>
	 */
	ShipmentChecked("ShipmentChecked", "Invoice$ShipmentChecked", ControlType.DEFAULT);

	/**
	 * <b>BPMO Identifier</b>
	 */
	public final static IBOId $BPMO = new IBOId.BOId("Invoice");

	/**
	 * <b>Node Identifier</b>
	 */
	public final static IBONode $ID = new IBONode.BONode("Invoice", "Invoice", ControlType.DEFAULT);

	private final static IEnumInspector ENUM_INSPECTOR = DataTypeFactory.getInstance().createEnumInspector(Invoice.class);
	private Invoice(String id, String path, ControlType controlType) { this.id = id; this.path = path; this.controlType = controlType; }
	private String id;
	public String getId() { return id; }
	private String path;
	public String getPath() { return path; }
	private ControlType controlType;
	public ControlType getControlType() { return controlType; }

	/**
	 * Retrieve a content element for a given path.
	 * 
	 * @return
	 *             Element requested or {@link IEnumInspector}.UNDEFINED_DATA if element does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a content element.
	 */
	public static IBODataElement getIBODataElementByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBODataElementByPath(path); }

	/**
	 * Retrieve a node for a given path.
	 * 
	 * @return
	 *             Node requested or {@link IEnumInspector}.UNDEFINED_NODE if node does not exist. 
	 * @throws ClassCastException
	 *             when the corresponding element for given path is not a node.
	 */ 
	public static IBONode getIBONodeByPath(String path) throws ClassCastException { return ENUM_INSPECTOR.getIBONodeByPath(path); }

}

