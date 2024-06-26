import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IPurchaseFromOtherResources } from 'app/shared/model/purchase-from-other-resources.model';
import { IDraft } from 'app/shared/model/draft.model';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { IDraftStatusInfo } from 'app/shared/model/draft-status-info.model';
import { ICategory } from 'app/shared/model/category.model';

export interface ICategoryElement {
  id?: number;
  value?: string | null;
  categoryName?: string | null;
  code?: string | null;
  orderRegType?: IOrderRegistrationInfo | null;
  requestType?: IOrderRegistrationInfo | null;
  importType?: IOrderRegistrationInfo | null;
  operationTyp?: IOrderRegistrationInfo | null;
  currencyProvisionType?: IOrderRegistrationInfo | null;
  paymentTool?: IOrderRegistrationInfo | null;
  activityType?: IOrderRegistrationInfo | null;
  ownerType?: IOrderRegistrationInfo | null;
  status?: IOrderRegistrationInfo | null;
  externalCustomerType?: IOrderRegistrationInfo | null;
  transportType?: IOrderRegistrationInfo | null;
  currencySupplier?: IPurchaseFromOtherResources | null;
  statusPurchase?: IPurchaseFromOtherResources | null;
  transportVehicleType?: IOrderRegistrationInfo | null;
  freightLetterType?: IDraft | null;
  actionCode?: IDraft | null;
  ownershipCode?: IDraft | null;
  currencyContainerPlace?: IDraft | null;
  draftSource?: IDraft | null;
  chargedExchangeBroker?: IDraft | null;
  impartType?: IDraft | null;
  insuranceLetterType?: IDraft | null;
  advisorDepositType?: IDraft | null;
  interfaceAdvisorDepositType?: IDraft | null;
  paymentType?: IDraft | null;
  dealType?: IDraft | null;
  coveringAdvisorDepositType?: IDraft | null;
  depositType?: IAdvisorDefinition | null;
  type?: IDraftType | null;
  secondaryType?: IDraftType | null;
  division?: IDraftType | null;
  productDimension?: IDraftReceipt | null;
  stateOfDocuments?: IDraftReceipt | null;
  currencyProvisionFileType?: IDraftReceipt | null;
  statusDraft?: IDraftStatusInfo | null;
  categoryElement?: ICategory | null;
}

export const defaultValue: Readonly<ICategoryElement> = {};
