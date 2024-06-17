import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/order-registration-info">
        <Translate contentKey="global.menu.entities.orderRegistrationInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/category-element">
        <Translate contentKey="global.menu.entities.categoryElement" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/custom">
        <Translate contentKey="global.menu.entities.custom" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-reg-serv">
        <Translate contentKey="global.menu.entities.orderRegServ" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        <Translate contentKey="global.menu.entities.product" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/licence-info">
        <Translate contentKey="global.menu.entities.licenceInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/purchase-from-other-resources">
        <Translate contentKey="global.menu.entities.purchaseFromOtherResources" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft">
        <Translate contentKey="global.menu.entities.draft" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/service-tariff">
        <Translate contentKey="global.menu.entities.serviceTariff" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-status-info">
        <Translate contentKey="global.menu.entities.draftStatusInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-receipt">
        <Translate contentKey="global.menu.entities.draftReceipt" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-custom-justification">
        <Translate contentKey="global.menu.entities.draftCustomJustification" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-factor">
        <Translate contentKey="global.menu.entities.draftFactor" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-used-assurance">
        <Translate contentKey="global.menu.entities.draftUsedAssurance" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/insurance-company-info">
        <Translate contentKey="global.menu.entities.insuranceCompanyInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/advisor-definition">
        <Translate contentKey="global.menu.entities.advisorDefinition" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-type">
        <Translate contentKey="global.menu.entities.draftType" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/audit-company-info">
        <Translate contentKey="global.menu.entities.auditCompanyInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-extend">
        <Translate contentKey="global.menu.entities.draftExtend" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/draft-tax">
        <Translate contentKey="global.menu.entities.draftTax" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/category">
        <Translate contentKey="global.menu.entities.category" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
