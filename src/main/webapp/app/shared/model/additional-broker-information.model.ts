export interface IAdditionalBrokerInformation {
  id?: number;
  dateOfStartRelation?: string | null;
  creditLimit?: string | null;
  revokedDate?: string | null;
  revokedNote?: string | null;
  confidential?: string | null;
  otherBrokerServices?: string | null;
  sanctionedStatus?: string | null;
  considerations?: string | null;
  description?: string | null;
  waysOfCommunication?: string | null;
  servicesAvailable?: string | null;
  customerAcceptancePolicy?: string | null;
}

export const defaultValue: Readonly<IAdditionalBrokerInformation> = {};
