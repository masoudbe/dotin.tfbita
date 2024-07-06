export interface IDraftAccountInfo {
  id?: number;
  documentReceiptDisciplinaryAccountId?: number | null;
  draftMainAccountId?: number | null;
  insuranceCostAccountId?: number | null;
  justificationDisciplinaryAccountId?: number | null;
  openDraftDisciplinaryAccountId?: number | null;
  otherCostsAccountId?: number | null;
  postSwiftCostsAccountId?: number | null;
  amountDeductionAccountId?: number | null;
}

export const defaultValue: Readonly<IDraftAccountInfo> = {};
