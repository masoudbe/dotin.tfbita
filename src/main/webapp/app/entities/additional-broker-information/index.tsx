import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AdditionalBrokerInformation from './additional-broker-information';
import AdditionalBrokerInformationDetail from './additional-broker-information-detail';
import AdditionalBrokerInformationUpdate from './additional-broker-information-update';
import AdditionalBrokerInformationDeleteDialog from './additional-broker-information-delete-dialog';

const AdditionalBrokerInformationRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AdditionalBrokerInformation />} />
    <Route path="new" element={<AdditionalBrokerInformationUpdate />} />
    <Route path=":id">
      <Route index element={<AdditionalBrokerInformationDetail />} />
      <Route path="edit" element={<AdditionalBrokerInformationUpdate />} />
      <Route path="delete" element={<AdditionalBrokerInformationDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AdditionalBrokerInformationRoutes;
