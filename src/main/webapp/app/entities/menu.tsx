import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/region">
        <Translate contentKey="global.menu.entities.region" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/country">
        <Translate contentKey="global.menu.entities.country" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/location">
        <Translate contentKey="global.menu.entities.location" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/department">
        <Translate contentKey="global.menu.entities.department" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/task">
        <Translate contentKey="global.menu.entities.task" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee">
        <Translate contentKey="global.menu.entities.employee" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/job">
        <Translate contentKey="global.menu.entities.job" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/job-history">
        <Translate contentKey="global.menu.entities.jobHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-registration-info">
        <Translate contentKey="global.menu.entities.orderRegistrationInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/category-element">
        <Translate contentKey="global.menu.entities.categoryElement" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/custom">
        <Translate contentKey="global.menu.entities.custom" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/licence-info">
        <Translate contentKey="global.menu.entities.licenceInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-reg-serv">
        <Translate contentKey="global.menu.entities.orderRegServ" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        <Translate contentKey="global.menu.entities.product" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/purchase-from-other-resources">
        <Translate contentKey="global.menu.entities.purchaseFromOtherResources" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
