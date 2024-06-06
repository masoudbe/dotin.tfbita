import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PurchaseFromOtherResources from './purchase-from-other-resources';
import PurchaseFromOtherResourcesDetail from './purchase-from-other-resources-detail';
import PurchaseFromOtherResourcesUpdate from './purchase-from-other-resources-update';
import PurchaseFromOtherResourcesDeleteDialog from './purchase-from-other-resources-delete-dialog';

const PurchaseFromOtherResourcesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PurchaseFromOtherResources />} />
    <Route path="new" element={<PurchaseFromOtherResourcesUpdate />} />
    <Route path=":id">
      <Route index element={<PurchaseFromOtherResourcesDetail />} />
      <Route path="edit" element={<PurchaseFromOtherResourcesUpdate />} />
      <Route path="delete" element={<PurchaseFromOtherResourcesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PurchaseFromOtherResourcesRoutes;
